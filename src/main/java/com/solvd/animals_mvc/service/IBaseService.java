package com.solvd.animals_mvc.service;

import java.util.List;

public interface IBaseService<T> {
    Long create(T entity);

    T retrieveById(Long id);

    void updateById(T entity, Long id);

    void delete(T entity);

    List<T> retrieveAll();
}
