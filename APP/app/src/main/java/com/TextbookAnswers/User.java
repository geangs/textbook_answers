package com.textbookanswers;

public class User {
    public String login,name,email,password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
