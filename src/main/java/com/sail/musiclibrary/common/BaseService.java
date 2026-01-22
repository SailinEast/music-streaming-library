package com.sail.musiclibrary.common;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T, ID> {
    protected abstract JpaRepository<T, ID> getRepository();

    public T findById(ID id) {
        return getRepository().findById(id).orElseThrow();
    }
}
