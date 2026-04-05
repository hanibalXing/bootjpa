package com.gx.bootjpa.service;

import com.gx.bootjpa.model.Student;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentService {
    List<Student> getStudentByClassNames(@Param("names") List<String> names);
}
