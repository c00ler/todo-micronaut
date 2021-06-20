package com.github.avenderov.todo;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class ApplicationTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void shouldRun() {
        assertThat(application.isRunning()).isTrue();
    }
}
