package com.easyjob.jobmanager.dto.user;

import com.easyjob.jobmanager.entity.user.User;

public class UserDto {

    private User user;
    private String token;

    public UserDto() {
    }

    public UserDto(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
