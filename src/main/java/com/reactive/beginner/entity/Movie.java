package com.reactive.beginner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Movie {
    private String name;
    private Integer year;
    private String genre;
    private List<Actor> actors;
}
