package com.gokhanakbas.veritabanproje.data.entity.entity;

import java.io.Serializable;

public class Actor implements Serializable {
    public Actor(int actor_id, String actor_name, String actor_country, String actor_gender, String actor_age) {
        this.actor_id = actor_id;
        this.actor_name = actor_name;
        this.actor_country = actor_country;
        this.actor_gender = actor_gender;
        this.actor_age = actor_age;
    }
    public Actor(int actor_id, String actor_name) {
        this.actor_id = actor_id;
        this.actor_name = actor_name;
    }
    public int getActor_id() {
        return actor_id;
    }

    public String getActor_name() {
        return actor_name;
    }

    public String getActor_country() {
        return actor_country;
    }

    public String getActor_age() {
        return actor_age;
    }
    public String getActor_gender() {
        return actor_gender;
    }



    int actor_id;
    String actor_name;



    String actor_country;

    String actor_gender;
    String actor_age;

}
