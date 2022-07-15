package com.example.demo_07_15.repository;

import com.example.demo_07_15.domain.Student;
import com.example.demo_07_15.domain.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.Collection;

public interface StudentRepository {

    Collection<Student> getAllStudents();
    Student getStudentById(String id);

    Student addStudent(StudentDTO dto);

    Student updateStudent(Student student);

    Student deleteStudentById(String id);
}
