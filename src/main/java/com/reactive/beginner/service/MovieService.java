package com.reactive.beginner.service;

import com.reactive.beginner.dataservice.DataServiceApi;
import com.reactive.beginner.entity.Actor;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import com.reactive.beginner.entity.Movie;
import reactor.core.publisher.Flux;

@Singleton
public class MovieService {

    @Inject
    DataServiceApi dataServiceApi;

    public Flux<Movie> getAllMovies() {
        return dataServiceApi.getAllMovies();
    }

    public Flux<Actor> getAllActors() {
        return dataServiceApi.getAllActors();
    }

    public Flux<Movie> findAllMoviesWithGenreAndMadeAfterYear(String genre, long year) {
        Flux<Movie> moviesWithAgeAndYear = dataServiceApi.getAllMovies();
        return moviesWithAgeAndYear
                .filter(movie -> movie.getGenre().equals(genre))
                .filter(movie -> movie.getYear() > year);
    }

    public Flux<Movie> changeAllMoviesToUpperCaseAndYearInTitle() {
        Flux<Movie> movies = dataServiceApi.getAllMovies();
        return movies
                .map(movie -> {
                    movie.setName(movie.getName().toUpperCase());
                    return movie;
                })
                .map(movie -> {
                    movie.setName(movie.getName() + " - " + movie.getYear());
                    return movie;
                });
    }

    public Flux<Actor> findAllActorsOlderThanAge(Integer age) {
        Flux<Actor> actorsWithAge = dataServiceApi.getAllActors();
        return actorsWithAge
                .filter(actor -> actor.getAge() > age);
    }
}
