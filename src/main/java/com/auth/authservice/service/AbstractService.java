package com.auth.authservice.service;

import com.auth.authservice.domain.BackwardCompatiblePage;
import com.auth.authservice.entity.BaseEntity;
import com.auth.authservice.mapper.BaseMapper;
import com.auth.authservice.repository.BaseRepository;
import com.auth.authservice.util.NumberUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public abstract class AbstractService<Dto, Entity extends BaseEntity> implements BasicOperation<Dto> {

    protected final static Map<String, Object> LOCK = new HashMap<>();

    private final BaseRepository<Entity, String> repository;

    private final BaseMapper<Dto, Entity> mapper;

    private final Class<Entity> aClass;

    @Autowired
    private MongoTemplate mongoTemplate;

    public AbstractService(final BaseRepository<Entity, String> repository, final BaseMapper<Dto, Entity> mapper,
                           final Class<Entity> aClass) {
        this.repository = repository;
        this.mapper = mapper;
        this.aClass = aClass;
    }


    public Page<Dto> toPageOfDtos(final Page<Entity> page) {
        final List<Dto> dtos = toDtos(page.getContent());
        return new BackwardCompatiblePage<>(page.getPageable(), page.getTotalElements(), dtos);
    }

    protected Dto toDto(final Entity entity) {
        return mapper.toDto(entity);
    }

    protected List<Dto> toDtos(final List<Entity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<Dto> findAll(final Pageable pageable) {
        final Page<Entity> page = repository.findAll(pageable);
        return toPageOfDtos(page);
    }

    @Override
    public List<Dto> findAll() {
        List<Entity> entities = repository.findAll();
        return toDtos(entities);
    }

    @Override
    public Dto findById(final String id) {
        final Entity entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("entity not found with id: " + id));
        return toDto(entity);
    }

    @Override
    public Dto save(final Dto dto) {
        final Entity entity = mapper.toEntity(dto);
        String id = entity.getId();
        if (StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString();
        }
        LOCK.putIfAbsent(id, new Object());
        synchronized (LOCK.get(id)) {
            if (exists(entity.getId())) {
                final Entity oldEntity = repository.findById(entity.getId()).orElseThrow(() -> new IllegalArgumentException("entity not found with id: " + entity.getId()));
                entity.setCreatedDate(oldEntity.getCreatedDate());
            }
            if (entity.getId() == null || NumberUtil.toIntegerOrElseZero(entity.getId()) <= 0) {
                final Long sequenceId = getMaxId() + 1;
                entity.setId(String.valueOf(sequenceId));
            }
            if (entity.getCreatedDate() == null) {
                entity.setCreatedDate(new Date());
            }
            return toDto(repository.save(entity));
        }
    }

    public synchronized Long getMaxId() {
        Entity entity = repository.findTopByOrderByCreatedDateDesc();
        Long maxId = 0L;
        if (entity != null) {
            try {
                return Long.parseLong(entity.getId());
            } catch (Exception e) {
                return maxId;
            }
        }
        return maxId;
    }

    @Override
    public void delete(final String id) {
        repository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        return id != null && repository.existsById(id);
    }

    public Page<Dto> filter(Query query, Pageable pageable) {
        final long totalElements = mongoTemplate.count(query, aClass);
        query.with(pageable);
        List<Entity> result = mongoTemplate.find(query, aClass);
        return toPageOfDtos(new BackwardCompatiblePage(pageable, totalElements, result));
    }
}