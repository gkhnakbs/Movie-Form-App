package com.gokhanakbas.veritabanproje.data.entity.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {

    public Movie(int movie_id, String movie_name, String movie_desc, String movie_score, String movie_category, ArrayList<Actor> movie_actors, ArrayList<Comment> movie_comments) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_desc = movie_desc;
        this.movie_score = movie_score;
        this.movie_category = movie_category;
        this.movie_comments = movie_comments;
        this.movie_actors = movie_actors;
    }
    public Movie(int movie_id, String movie_name, String movie_desc, String movie_score) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_desc = movie_desc;
        this.movie_score = movie_score;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public String getMovie_desc() {
        return movie_desc;
    }

    public String getMovie_score() {
        return movie_score;
    }
    public String getMovie_category() {
        return movie_category;
    }
    public ArrayList<Comment> getMovie_comments() {
        return movie_comments;
    }

    public ArrayList<Actor> getMovie_actors() {
        return movie_actors;
    }


    int movie_id;
    String movie_name;
    String movie_desc;
    String movie_score;



    String movie_category;



    ArrayList<Comment> movie_comments;



    ArrayList<Actor> movie_actors;
}
