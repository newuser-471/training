package com.example.search.domain;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;


public class ResultList {

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private List<Employee> employeeList;

    @JsonProperty("message")
    private String message;
}
