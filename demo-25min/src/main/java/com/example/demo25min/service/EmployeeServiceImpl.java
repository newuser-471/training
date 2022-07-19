package com.example.demo25min.service;

import com.example.demo25min.domain.CommonResponse;
import com.example.demo25min.domain.Employee;
import com.example.demo25min.domain.RestList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private RestTemplate restTemplate;

    @Autowired
    public EmployeeServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private final String url = "https://dummy.restapiexample.com/api/v1/employees";

    @Override
    public CommonResponse getAllEmployee() {
        return new CommonResponse(restTemplate.getForObject(url, RestList.class));
    }

    @Override
    public CommonResponse getEmployeeOlderThan(Integer age) {
        List<Employee> res = restTemplate.getForObject(url, RestList.class).getEmployeeList().stream().filter(e->e.getAge()>=age).collect(Collectors.toList());
        return new CommonResponse(res);
    }
}
