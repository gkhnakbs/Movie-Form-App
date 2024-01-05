package com.gokhanakbas.veritabanproje.data.entity.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {

    public Movie(int movie_id, String movie_name, String movie_desc, String movie_score, String movie_category) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_desc = movie_desc;
        this.movie_score = movie_score;
        this.movie_category = movie_category;
    }
    public Movie(int movie_id, String movie_name, String movie_desc, String movie_score) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_desc = movie_desc;
        this.movie_score = movie_score;
    }

    public Movie(int movie_id, String movie_name) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
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

    /**
     * Pay attention here, you have to override the toString method as the
     * ArrayAdapter will reads the toString of the given object for the name
     *
     * @return contact_name
     */
    @Override
    public String toString() {
        return movie_name;
    }

    int movie_id;
    String movie_name;
    String movie_desc;
    String movie_score;
    String movie_category;
}
