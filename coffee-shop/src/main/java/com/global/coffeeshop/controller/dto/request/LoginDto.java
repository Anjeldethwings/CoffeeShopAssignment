package com.global.coffeeshop.controller.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginDto {

    @NotEmpty(message = "username shouldn't be empty value")
    @NotNull(message = "username shouldn't be null value")
    String userName;

    @NotEmpty(message = "password shouldn't be empty value")
    @NotNull(message = "password shouldn't be null value")
    String password;

    public LoginDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
