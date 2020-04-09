package com.springusertrack.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface DtoConverter<D, E> {

    E convertToEntity(D dto);

    D convertToDto(E entity);

    default List<D> convertToDtoList(Collection<E> entities) {
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    default List<E> convertToEntityList(Collection<D> dtos) {
        return dtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}
