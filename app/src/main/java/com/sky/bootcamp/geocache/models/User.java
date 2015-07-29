package com.sky.bootcamp.geocache.models;

import com.sky.bootcamp.geocache.database.Database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by mgh01 on 27/07/2015.
 */
public class User implements Serializable {

    private String password;
    private String email;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }
}


