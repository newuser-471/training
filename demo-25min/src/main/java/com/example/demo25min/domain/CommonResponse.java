package com.example.demo25min.domain;



public class CommonResponse {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public CommonResponse(Object data) {
        this.data = data;
    }
}
