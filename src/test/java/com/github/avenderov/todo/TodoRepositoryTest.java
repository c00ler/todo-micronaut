package com.github.avenderov.todo;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class TodoRepositoryTest {

    @Inject
    TodoRepository subject;

    @Test
    void shouldHaveTestData() {
        assertThat(subject.findAll())
            .filteredOn(TodoEntity::isCompleted)
            .map(TodoEntity::getTitle)
            .contains("Completed todo");
    }

    @Test
    void shouldReturnAllNonCompleted() {
        final var notCompletedTodo1 = new TodoEntity(UUID.randomUUID().toString());
        final var notCompletedTodo2 = new TodoEntity(UUID.randomUUID().toString());

        subject.saveAll(List.of(notCompletedTodo1, notCompletedTodo2));

        assertThat(subject.findAllNotCompleted())
            .hasSizeGreaterThanOrEqualTo(2)
            .allMatch(e -> !e.isCompleted())
            .map(TodoEntity::getTitle)
            .contains(notCompletedTodo1.getTitle(), notCompletedTodo2.getTitle());
    }
}
