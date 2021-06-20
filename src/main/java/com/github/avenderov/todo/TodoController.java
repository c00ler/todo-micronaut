package com.github.avenderov.todo;

import io.micronaut.data.model.Sort;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

@ExecuteOn(TaskExecutors.IO)
@Controller("/todo")
public class TodoController {

    private final TodoRepository todoRepository;

    @Inject
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Get
    public Iterable<TodoEntity> findAll() {
        return todoRepository.findAll(Sort.of(Sort.Order.desc("id")));
    }

    @Get("/{id}")
    public HttpResponse<TodoEntity> findById(Long id) {
        return todoRepository.findById(id)
            .map(HttpResponse::ok)
            .orElseGet(HttpResponse::notFound);
    }
}
