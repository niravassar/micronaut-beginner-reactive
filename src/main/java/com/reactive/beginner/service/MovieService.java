package com.reactive.beginner.service;

import com.reactive.beginner.dataservice.DataServiceApi;
import com.reactive.beginner.entity.Actor;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import com.reactive.beginner.entity.Movie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    public Flux<Movie> getAllMoviesWithUpperCaseAndYearInTitle() {
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

    public Flux<Movie> addJimCarreyToMovieAsActor() {
        Flux<Movie> movies = dataServiceApi.getAllMovies();
        Flux<Actor> actors = dataServiceApi.getAllActors();
        Flux<Movie> editedMovie =
          actors
             .filter(actor -> actor.getName().equals("Jim Carrey"))
             // need a flatmap other map give Flux of Flux
             .flatMap( jimCarreyActor -> {
                    Flux<Movie> myMovie = movies
                          .filter(movie -> movie.getName().equals("Dumb and Dumber"))
                          .map(dumbAndDumberMovie -> {
                              List<Actor> actorsForfDumbAndDumber = List.of(jimCarreyActor);
                              dumbAndDumberMovie.setActors(actorsForfDumbAndDumber);
                              // return the movie edited back to the steam
                              return dumbAndDumberMovie;
                          });
                    // return the stream with edited movie
                    return myMovie;
                });
        // return the stream back to method signature
        return editedMovie;
    }
}
