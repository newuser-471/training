package com.example.demo_07_15.service;

import com.example.demo_07_15.domain.CommonResponse;
import com.example.demo_07_15.domain.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface StudentService {
    @Transactional
    CommonResponse getAllStudents();

    @Transactional
    CommonResponse getStudentById(String id);

    @Transactional
    CommonResponse updateStudent(Student student);

    @Transactional
    String deleteStudentById(String id);
}
