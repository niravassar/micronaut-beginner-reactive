package com.reactive.beginner.controller;

import com.reactive.beginner.entity.Actor;
import com.reactive.beginner.entity.Movie;
import com.reactive.beginner.service.MovieService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import reactor.core.publisher.Flux;

@Controller()
public class MovieController {

    @Inject
    MovieService movieService;

    @Get("/hello")
    String sayHello() {
        return "Hello";
    }

    @Get("/findDramaAfter78")
    Flux<Movie> findDramaAfter78() {
        return movieService.findAllMoviesWithGenreAndMadeAfterYear(Movie.DRAMA, 1978);
    }

    @Get("/returnMoviesAndYear")
    Flux<Movie> changeAllMoviesToUpperCaseAndYearInTitle() {
        return movieService.changeAllMoviesToUpperCaseAndYearInTitle();
    }

    @Get("/actorsOlder64")
    Flux<Actor> findAllActorsOlderThan64() {
        return movieService.findAllActorsOlderThanAge(64).log();
    }
}
