package com.auth.authservice.web.common;

@FunctionalInterface
public interface ApiCall<T> {
    T call();
}
