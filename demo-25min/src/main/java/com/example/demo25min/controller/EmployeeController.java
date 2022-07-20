package com.example.demo25min.controller;

import com.example.demo25min.domain.CommonResponse;
import com.example.demo25min.domain.Employee;
import com.example.demo25min.domain.RestList;
import com.example.demo25min.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService= employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<CommonResponse> getAllEmployee(){
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @GetMapping(value = "/",params = "age")
    public ResponseEntity<CommonResponse> getEmployeeWithAgeAbove(@RequestParam Integer age){
        return new ResponseEntity<>(employeeService.getEmployeeOlderThan(age),HttpStatus.OK);
    }
}
