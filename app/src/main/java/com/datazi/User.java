package com.datazi;

/**
 * Created by Ashish on 3/1/2018.
 */

public class User {
    String name;
    int id;

    //default cons
    public User(){

    }
    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
