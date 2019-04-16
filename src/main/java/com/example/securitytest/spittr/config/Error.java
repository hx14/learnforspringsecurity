package com.example.securitytest.spittr.config;

/**
 * Created by hx on 2019-04-16.
 */
public class Error {
    private int code;
    private String message;
    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
