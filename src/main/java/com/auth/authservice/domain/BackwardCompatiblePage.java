package com.auth.authservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

public class BackwardCompatiblePage<T> extends PageImpl<T> {

    @JsonIgnore
    @Override
    public Sort getSort() {
        return super.getSort();
    }

    @JsonIgnore
    @Override
    public Pageable getPageable() {
        return super.getPageable();
    }

    @JsonIgnore
    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }


    public BackwardCompatiblePage(final Integer page, final Integer size, final long totalElements, final List<T> elements) {
        super(elements, PageRequest.of(page, size), totalElements);
    }

    public BackwardCompatiblePage(final Pageable pageable, final long totalElements, final List<T> elements) {
        super(elements, pageable, totalElements);
    }


    public static <E> BackwardCompatiblePage<E> customPageResult(final List<E> list, final int page, final int size) {
        if (list == null || list.isEmpty()) {
            return new BackwardCompatiblePage<E>(page, size, 0, Collections.emptyList());
        }
        final int from = page * size;
        if (from > list.size()) {
            return new BackwardCompatiblePage<E>(page, size, list.size(), Collections.emptyList());
        }
        final int to = from + size;
        return new BackwardCompatiblePage<E>(page, size, list.size(), list.subList(from, Math.min(to, list.size())));
    }

    public static <E> BackwardCompatiblePage<E> customPageResult(final List<E> list, final Pageable pageable) {
        final int page = pageable.getPageNumber();
        final int size = pageable.getPageSize();
        return customPageResult(list, page, size);
    }
}