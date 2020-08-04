package com.movieapp.razan.login.model;

import android.text.TextUtils;

public class UsersModel {
    private String email;
    private String password;

    public UsersModel() {
    }

    public UsersModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValidData() {
        return !TextUtils.isEmpty(getEmail()) && !TextUtils.isEmpty(getPassword());
    }
}
