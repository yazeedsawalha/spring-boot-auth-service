package com.auth.authservice.mapper;


import com.auth.authservice.domain.UserContext;
import com.auth.authservice.domain.UserContextHolder;
import com.auth.authservice.entity.common.Language;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class BaseMapper<Dto, Entity> {
    public abstract Dto toDto(final Entity entity);

    public List<Dto> toDtos(final List<Entity> entities) {
        return entities.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList());
    }

    public abstract Entity toEntity(final Dto dto);

    public List<Entity> toEntities(final List<Dto> dtos) {
        return dtos.stream().filter(Objects::nonNull).map(this::toEntity).collect(Collectors.toList());
    }

    protected UserContext getCurrentUserContext() {
        return UserContextHolder.get();
    }

    protected Language getLanguage() {
        return Language.resolve(getCurrentUserContext().getLanguage());
    }

}