package com.example.assignment4.dto;

public class CommonResponse {

    private Object data;

    public CommonResponse(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
