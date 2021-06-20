package com.github.avenderov.todo;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.Sort;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface TodoRepository extends PageableRepository<TodoEntity, Long> {

    Sort ORDER_BY_ID_DESC = Sort.of(Sort.Order.desc("id"));

    /**
     * Override method from {@link PageableRepository} to change return type from {@link Iterable} to {@link List}.
     */
    @NonNull
    @Override
    List<TodoEntity> findAll(@NonNull Sort sort);
}
