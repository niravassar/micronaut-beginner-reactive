package com.reactive.beginner.controller;

import com.reactive.beginner.entity.Actor;
import com.reactive.beginner.entity.Movie;
import com.reactive.beginner.service.MovieService;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class MovieControllerTest {

    @Inject
    @Client("/")
    private HttpClient httpClient;

    @Test
    void test_blocking_actorsOlder64() {
        HttpRequest<?> request = HttpRequest.GET("/actorsOlder64");
        HttpResponse<List<Actor>> response = httpClient.toBlocking().exchange(request, Argument.listOf(Actor.class));
        List<Actor> actors = response.body();
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Harrison Ford", actors.get(0).getName());
        assertEquals("Bill Murray", actors.get(1).getName());
    }
}
