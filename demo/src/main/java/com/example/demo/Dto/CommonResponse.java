package com.example.demo.Dto;

public class CommonResponse {

    public CommonResponse(Object data) {
        this.data = data;
    }

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
