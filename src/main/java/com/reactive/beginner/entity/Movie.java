package com.reactive.beginner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Movie {

    public static String ACTION = "action";
    public static String COMEDY = "comedy";
    public static String DRAMA = "drama";

    private String name;
    private Integer year;
    private String genre;
    private List<Actor> actors;
}
