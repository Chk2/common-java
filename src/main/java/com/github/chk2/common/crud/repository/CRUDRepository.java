package com.github.chk2.common.crud.repository;

import com.github.chk2.common.crud.model.MyEntity;

import java.io.Serializable;
import java.util.List;

public interface CRUDRepository<E extends MyEntity<I>, I extends Serializable> {

    List<E> findAll();

    List<E> findByIdIn(List<I> ids);

    E findById(I id);

    E save(E entity);

    void delete(I id);

    void disable(I id);

    void enable(I id);
}
