package com.solvd.animals_mvc.dao;

import java.util.List;

public interface IBaseDAO <T>{
    T getEntityById(Long id);

    Long insertEntity(T entity);

    void updateEntity(T entity,Long id);

    void removeEntity(T entity);

    List<T> getEntities();
}
