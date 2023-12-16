package com.gokhanakbas.veritabanproje.data.entity.entity;

public class Actor {
    public Actor(int actor_id, String actor_name, String actor_country, String actor_age) {
        this.actor_id = actor_id;
        this.actor_name = actor_name;
        this.actor_country = actor_country;
        this.actor_age = actor_age;
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

    int actor_id;
    String actor_name;
    String actor_country;
    String actor_age;

}
