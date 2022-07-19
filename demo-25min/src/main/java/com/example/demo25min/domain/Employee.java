package com.example.demo25min.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee{

    @JsonProperty("Id")
    private int id;

    @JsonProperty("EmployeeName")
    private String name;

    @JsonProperty("EmployeeSalary")
    private int salary;

    @JsonProperty("EmployeeAge")
    private int age;

    @JsonProperty("ProfileImage")
    private String image;

}
