package com.global.coffeeshop.controller.dto;

public class ErrorResponseDto {

    private String errorMsg;

    public ErrorResponseDto(String errorMsg){
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
