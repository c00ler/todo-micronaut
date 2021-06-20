package com.github.avenderov.todo;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class StartupEventListener implements ApplicationEventListener<StartupEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(StartupEventListener.class);

    private final TodoRepository todoRepository;

    @Inject
    public StartupEventListener(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void onApplicationEvent(StartupEvent event) {
        LOG.info("Delete all");
        todoRepository.deleteAll();

        LOG.info("Setup data");
        todoRepository.setupData();
    }
}
