package com.f_in.foodyingnutty.Models;

import androidx.annotation.NonNull;

public class User {
    String uid;
    String email,name;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {

    }

    @NonNull
    @Override
    public String toString() {
        return "User{"+
                "uid-'"+ uid + '\''+
                ", email-'" + email + '\'' +
                ", name-'" + name + '\'' +
                '}';
    }
}
