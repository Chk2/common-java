package com.github.chk2.common.crud.repository;

import com.github.chk2.common.crud.model.MyEntity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCRUDRepository<E extends MyEntity<I>, I extends Number> implements CRUDRepository<E, I> {

    private Map<I, E> entities = new HashMap<>();
    private int counterId = 1;

    public InMemoryCRUDRepository() {
    }

    @Override
    public List<E> findAll() {
        List<E> result = new ArrayList<>();

        for (I eId : entities.keySet()) {
            result.add(getEntityFromCollection(eId));
        }

        return result;
    }

    @Override
    public List<E> findByIdIn(List<I> ids) {
        List<E> result = new ArrayList<>();

        for (E e : findAll()) {
            if (ids.contains(e.getId())) {
                result.add(e);
            }
        }

        return result;
    }

    @Override
    public E findById(I id) {
        E entity = getEntityFromCollection(id);

        if (entity == null) {
            return null;
        }

        return entity;
    }

    @Override
    public E save(E entity) {
        I id = entity.getId();

        if (id == null || id.equals(0)) {
            id = nextId();
        }

        if (id.intValue() > counterId) {
            nextId();
        }

        E entityToBeSaved = copy(entity);

        @SuppressWarnings("unchecked")
        Class<? extends MyEntity<I>> c = (Class<? extends MyEntity<I>>) entityToBeSaved.getClass();

        Field idField;
        try {
            idField = c.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(entityToBeSaved, id);

        } catch (Exception e) {
            throw new RuntimeException("Error while setting the entity id");
        }

        entities.put(id, entityToBeSaved);

        return entityToBeSaved;
    }

    @Override
    public void delete(I id) {
        entities.remove(id);
    }

    @Override
    public void disable(I id) {
        E byId = findById(id);

        if (byId != null) {
            byId.disable();
            save(byId);
        }
    }

    @Override
    public void enable(I id) {
        E byId = findById(id);

        if (byId != null) {
            byId.enable();
            save(byId);
        }
    }

    private E getEntityFromCollection(I eId) {
        E entity = entities.get(eId);

        if (entity == null) {
            return null;
        }

        return copy(entity);
    }

    @SuppressWarnings("unchecked")
    private I nextId() {
        Integer id = counterId;
        counterId++;

        return ((I) id);
    }

    @SuppressWarnings("unchecked")
    private E copy(E entity) {
        Class<?> entityClass = entity.getClass();

        E newEntity;

        try {
            Constructor<?> constructor = entityClass.getConstructor();
            newEntity = (E) constructor.newInstance();

        } catch (Exception e) {
            throw new CannotCloneEntityException("Error while cloning entity, don't forget default constructor", e);
        }

        Field[] fields = entityClass.getDeclaredFields();

        for (Field f : fields) {
            f.setAccessible(true);
            try {
                f.set(newEntity, f.get(entity));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Fuck");
            }
        }

        return newEntity;
    }

    public static class CannotCloneEntityException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public CannotCloneEntityException(String msg, Exception e) {
            super(msg, e);
        }
    }
}
