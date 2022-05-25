package com.auth.authservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends MongoRepository<T, ID> {
    T findTopByOrderByCreatedDateDesc();
}