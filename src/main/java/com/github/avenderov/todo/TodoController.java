package com.github.avenderov.todo;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/todo")
public class TodoController {

    private final TodoRepository todoRepository;

    @Inject
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Get
    public List<TodoEntity> findAll() {
        return todoRepository.findAll(TodoRepository.ORDER_BY_ID_DESC);
    }

    @Get("/{id}")
    public HttpResponse<TodoEntity> findById(Long id) {
        return todoRepository.findById(id)
            .map(HttpResponse::ok)
            .orElseGet(HttpResponse::notFound);
    }

    @Put
    public void update(@Body TodoEntity entity) {
        todoRepository.update(entity);
    }

    @Post
    public TodoEntity create(@Body TodoEntity entity) {
        return todoRepository.save(entity);
    }

    @Delete("/{id}")
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}
