package com.example.search.service;

import com.example.search.domain.CommonResponse;
import com.example.search.domain.Employee;
import com.example.search.domain.ResultList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final String url = "https://dummy.restapiexample.com/api/v1/employees";

    private RestTemplate restTemplate;

    @Autowired
    public EmployeeServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public CommonResponse getAllEmployee() {
        return new CommonResponse(restTemplate.getForObject(url, ResultList.class));
    }

    @Override
    @Async("customExecutor")
    public CompletableFuture getEmployeeWithAges(List<Integer> ages) {
        long start = System.currentTimeMillis();
        log.info("getting data from external url");
        List<Employee> res = restTemplate.getForObject(url, ResultList.class)
                .getEmployeeList().stream().filter(e->ages.contains(e.getAge()))
                .collect(Collectors.toList());

        long end = System.currentTimeMillis();
        long cost = end-start;
        log.warn(Thread.currentThread().getName()+" cost "+cost);
        return CompletableFuture.completedFuture(res);
    }
}
