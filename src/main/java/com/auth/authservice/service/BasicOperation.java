package com.auth.authservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface BasicOperation<Dto> {
    Page<Dto> findAll(final Pageable pageable);

    Dto findById(final String id);

    List<Dto> findAll();

    Dto save(final Dto dto);

    void delete(final String id);

    Page<Dto> filter(final Query query, final Pageable pageable);

    boolean exists(final String id);
}
