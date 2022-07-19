package com.example.demo25min.service;

import com.example.demo25min.domain.CommonResponse;

public interface EmployeeService {

    CommonResponse getAllEmployee();

    CommonResponse getEmployeeOlderThan(Integer age);
}
