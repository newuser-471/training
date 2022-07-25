package com.example.search.controller;


import com.example.search.domain.CommonResponse;
import com.example.search.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    @GetMapping(value = "/",params = "ages")
    public ResponseEntity<CommonResponse> getEmployeeWithGivenAges(@RequestParam List<Integer> ages) throws ExecutionException, InterruptedException {

        return new ResponseEntity<>(new CommonResponse(employeeService.getEmployeeWithAges(ages).get()),HttpStatus.OK);
    }
}
