package com.example.demo_07_15.service;

import com.example.demo_07_15.domain.CommonResponse;
import com.example.demo_07_15.domain.Student;
import com.example.demo_07_15.domain.StudentDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface StudentService {

    CommonResponse getAllStudents();

    CommonResponse getStudentById(String id);

    CommonResponse updateStudent(Student student);

    CommonResponse createStudent(StudentDTO dto);

    String deleteStudentById(String id);
}
