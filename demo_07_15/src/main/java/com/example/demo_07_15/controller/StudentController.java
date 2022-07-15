package com.example.demo_07_15.controller;

import com.example.demo_07_15.domain.CommonResponse;
import com.example.demo_07_15.domain.ErrorResponse;
import com.example.demo_07_15.domain.Student;
import com.example.demo_07_15.domain.StudentDTO;
import com.example.demo_07_15.exception.ResourceNotFoundException;
import com.example.demo_07_15.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<CommonResponse> getAllStudents(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<CommonResponse> getStudentById(@PathVariable String id) {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<CommonResponse> addStudent(@RequestBody StudentDTO student){
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.OK);
    }

    @PutMapping("/students")
    public ResponseEntity<CommonResponse> updateStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.updateStudent(student), HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable String id){
        return new ResponseEntity<>(studentService.deleteStudentById(id), HttpStatus.OK);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundHandler() {
        return new ResponseEntity<>(new ErrorResponse("this is the message", new Date()), HttpStatus.NOT_FOUND);
    }
}
