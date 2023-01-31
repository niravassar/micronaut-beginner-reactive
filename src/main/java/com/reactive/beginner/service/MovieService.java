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
}
