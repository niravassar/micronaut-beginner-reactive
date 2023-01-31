package com.reactive.beginner.service;

import com.reactive.beginner.entity.Actor;
import com.reactive.beginner.entity.Movie;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class MovieServiceTest {

    @Inject
    MovieService movieService;

    @Test
    void test_getAllMovies() {

        Flux<Movie> movies = movieService.getAllMovies().log();

        StepVerifier.create(movies)
                .expectNextCount(7)
                .verifyComplete();
    }

    @Test
    void test_getAllActors() {

        Flux<Actor> actors = movieService.getAllActors().log();

        StepVerifier.create(actors)
                .expectNextCount(7)
                .verifyComplete();
    }
}
