package com.shivam.dao;

import com.shivam.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "select avg(total_marks) from student where name like '%mmy'", nativeQuery = true)
    Double getAvgMarksOfAllStudents();

}
