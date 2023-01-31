package com.reactive.beginner.service;

import com.reactive.beginner.entity.Actor;
import com.reactive.beginner.entity.Movie;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

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

    @Test
    void test_findAllActorsOlderThanAge() {

        Flux<Actor> actors = movieService.findAllActorsOlderThanAge(64).log();

        StepVerifier.create(actors)
                .assertNext(actor -> {
                    assertEquals("Harrison Ford", actor.getName());
                    assertEquals(80, actor.getAge());
                })
                .assertNext(actor -> {
                    assertEquals("Bill Murray", actor.getName());
                    assertEquals(72, actor.getAge());
                })
                .assertNext(actor -> {
                    assertEquals("Kevin Costner", actor.getName());
                    assertEquals(68, actor.getAge());
                })
                .assertNext(actor -> {
                    assertEquals("Silvester Stallone", actor.getName());
                    assertEquals(76, actor.getAge());
                })
                .verifyComplete();
    }

    @Test
    void test_findAllMoviesWithGenreAndMadeAfterYear() {

        Flux<Movie> moviesGenreAndYear = movieService.findAllMoviesWithGenreAndMadeAfterYear(Movie.DRAMA, 1978).log();

        StepVerifier.create(moviesGenreAndYear)
                .assertNext(movie -> {
                    assertEquals("Dances With Wolves", movie.getName());
                    assertEquals(1990, movie.getYear());
                })
                .verifyComplete();
    }
}
