package com.example.demo_07_15.repository;

import com.example.demo_07_15.domain.Student;
import com.example.demo_07_15.domain.StudentDTO;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

    private Map<String, Student> studentMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        Student s1 = new Student("1", "Tom", true);
        Student s2 = new Student("2", "Jerry", true);
        studentMap.put(s1.getId(), s1);
        studentMap.put(s2.getId(), s2);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return studentMap.values();
    }

    @Override
    public Student getStudentById(String id) {
        return studentMap.get(id);
    }

    @Override
    public Student addStudent(StudentDTO dto) {
        Student stu = new Student(studentMap.size()+1+"", dto.getName(), false);
        studentMap.put(studentMap.size()+1+"",stu);
        return stu;
    }

    @Override
    public Student updateStudent(Student student) {
        String id = student.getId();
        return studentMap.put(id, student);
    }


    @Override
    public Student deleteStudentById(String id) {
        return studentMap.remove(id);
    }
}
