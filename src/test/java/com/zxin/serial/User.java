package com.zxin.serial;
public class User {

    private String userName;

    private String email;

    public User() {}

     public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String toString() {
        return "User:{userName=" + this.userName + ",email=" + this.email + "}";
    }

    //Getter and Setter...
}