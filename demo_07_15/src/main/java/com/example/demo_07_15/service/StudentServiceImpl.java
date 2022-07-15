package com.example.demo_07_15.service;

import com.example.demo_07_15.domain.CommonResponse;
import com.example.demo_07_15.domain.Student;
import com.example.demo_07_15.domain.StudentResponseDTO;
import com.example.demo_07_15.exception.ResourceNotFoundException;
import com.example.demo_07_15.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public CommonResponse getAllStudents() {
        CommonResponse response = new CommonResponse();
        List<StudentResponseDTO> studentResponseDTOS = studentRepository.getAllStudents().stream().map(s -> new StudentResponseDTO(s)).collect(Collectors.toList());
        response.setData(studentResponseDTOS);
        return response;
    }

    @Override
    public CommonResponse getStudentById(String id) {

        CommonResponse response = new CommonResponse();
        Student stu = studentRepository.getStudentById(id);
        if(stu == null) {
            log.error("{} id cannot be found in system", id);
            throw new ResourceNotFoundException("........");
        }
        response.setData(new StudentResponseDTO(stu));
        return response;
    }

    @Override
    public CommonResponse updateStudent(Student student) {
        CommonResponse response = new CommonResponse();
        Student stu = studentRepository.updateStudent(student);
        if(stu==null){
            throw new ResourceNotFoundException(student.getId() + "does not exist, update failed");
        }
        response.setData(new StudentResponseDTO(student));
        return response;
    }

    @Override
    public String deleteStudentById(String id) {
        Student stu = studentRepository.deleteStudentById(id);
        if(stu==null){
            throw new ResourceNotFoundException(id + "does not exist, delete failed");
        }
        return "delete successful";
    }
}
