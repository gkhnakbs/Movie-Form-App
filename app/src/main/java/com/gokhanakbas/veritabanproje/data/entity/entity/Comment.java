package com.gokhanakbas.veritabanproje.data.entity.entity;

import java.io.Serializable;

public class Comment implements Serializable {
    public Comment(int comment_id, int user_id, int movie_id, String comment_user_name, String comment_desc, String comment_user_score) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.comment_user_name = comment_user_name;
        this.comment_desc = comment_desc;
        this.comment_user_score = comment_user_score;
    }

    public Comment(int comment_id, int user_id, String comment_user_name, String comment_desc, String comment_user_score) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.comment_user_name = comment_user_name;
        this.comment_desc = comment_desc;
        this.comment_user_score = comment_user_score;
    }

    public int getComment_id() {
        return comment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getComment_desc() {
        return comment_desc;
    }

    public String getComment_user_score() {
        return comment_user_score;
    }

    public String getComment_user_name() {
        return comment_user_name;
    }

    int comment_id;
    int user_id;


    int movie_id;
    String comment_user_name;
    String comment_desc;
    String comment_user_score;
}

