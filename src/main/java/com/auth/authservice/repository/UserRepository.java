package com.auth.authservice.repository;

import com.auth.authservice.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User,String> {
    User findByUsername(String username);
}
