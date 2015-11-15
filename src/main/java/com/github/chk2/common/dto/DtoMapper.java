package com.github.chk2.common.dto;

import com.github.chk2.common.crud.model.MyEntity;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public interface DtoMapper<E extends MyEntity<? extends Serializable>, D extends Dto> {

    E entityFromDto(D dto);

    default List<E> allEntitiesFromDtos(List<D> dtos) {
        return dtos.stream().map(this::entityFromDto).collect(Collectors.toList());
    }

    D dtoFromEntity(E entity);

    default List<D> allDtosFromEntities(List<E> entities) {
        return entities.stream().map(this::dtoFromEntity).collect(Collectors.toList());
    }
}
