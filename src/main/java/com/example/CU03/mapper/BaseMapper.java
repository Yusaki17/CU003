package com.example.CU03.mapper;

import java.util.List;

public interface BaseMapper<E, D> {
    D toDTO(E entity);
    E toEntity(D dto);
    List<D> toDTOs(List<E> list);
    List<E> toEntityList(List<D> list);
}