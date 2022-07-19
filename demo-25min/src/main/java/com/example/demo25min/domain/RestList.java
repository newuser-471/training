package com.example.demo25min.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestList {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private List<Employee> employeeList;

    @JsonProperty("message")
    private String message;
}
