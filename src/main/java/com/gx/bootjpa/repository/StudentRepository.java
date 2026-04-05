package com.gx.bootjpa.repository;

import com.gx.bootjpa.model.Permission;
import com.gx.bootjpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "select s from Student s left join fetch s.classes where  (s.classes is null) or s.classes.name in (:names)")
    List<Student> getStudentByClassNames(@Param("names") List<String> names);
}
