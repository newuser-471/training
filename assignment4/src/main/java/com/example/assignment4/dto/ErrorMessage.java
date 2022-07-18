package com.example.assignment4.dto;

import java.util.Date;

public class ErrorMessage {

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private Date date;

    public ErrorMessage(Date date, String message) {
        this.date = date;
        this.message = message;
    }

    private String message;
}
