package com.gx.bootjpa.service.impl;

import com.gx.bootjpa.model.Student;
import com.gx.bootjpa.repository.StudentRepository;
import com.gx.bootjpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public List<Student> getStudentByClassNames(List<String> names) {
        return studentRepository.getStudentByClassNames(names);
    }
}
