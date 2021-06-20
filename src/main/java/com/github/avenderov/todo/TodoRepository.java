package com.github.avenderov.todo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface TodoRepository extends PageableRepository<TodoEntity, Long> {
}
