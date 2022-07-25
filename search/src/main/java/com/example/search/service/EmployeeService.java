package com.example.search.service;

import com.example.search.domain.CommonResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EmployeeService {

    CommonResponse getAllEmployee();

    CompletableFuture getEmployeeWithAges(List<Integer> ages);
}
