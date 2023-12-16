package com.gokhanakbas.veritabanproje.data.entity;

import java.util.ArrayList;

public class Movie {
    public Movie(int movie_id, String movie_name, String movie_desc, int movie_score, ArrayList<Integer> movie_comments, ArrayList<Integer> movie_actors) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_desc = movie_desc;
        this.movie_score = movie_score;
        this.movie_comments = movie_comments;
        this.movie_actors = movie_actors;
    }

    int movie_id;
    String movie_name;
    String movie_desc;
    int movie_score;
    ArrayList<Integer> movie_comments;
    ArrayList<Integer> movie_actors;
}
