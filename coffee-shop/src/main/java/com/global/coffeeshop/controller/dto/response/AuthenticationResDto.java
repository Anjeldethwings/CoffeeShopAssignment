package com.global.coffeeshop.controller.dto.response;

public class AuthenticationResDto {
    String jwt;

    public AuthenticationResDto(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
