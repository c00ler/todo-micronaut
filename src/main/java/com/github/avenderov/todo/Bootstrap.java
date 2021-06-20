package com.github.avenderov.todo;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class Bootstrap implements ApplicationEventListener<ServerStartupEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

    private final TodoRepository todoRepository;

    @Inject
    public Bootstrap(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        LOG.info("Cleaning up database.");
        todoRepository.findAll().forEach(todoRepository::delete);

        final var completed = new TodoEntity("Completed todo", /* completed= */ true);

        todoRepository.save(completed);
        LOG.info("Added todo entry");
    }
}
