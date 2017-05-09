package by.totema.recourse.document.model.mapper;

import by.totema.recourse.entity.model.BaseEntity;

import java.util.List;

public interface EntityToRowMapper<ID, E extends BaseEntity<ID>> {
    List<String> toRow(E entity);
}