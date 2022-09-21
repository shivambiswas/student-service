package com.shivam.dao;

import com.shivam.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @AfterEach
    void tearDown () {
        studentRepository.deleteAll();
    }

    @Test
    @DisplayName("Test for average marks of all students whose name has 'mmy'")
    void testAverageMarksOfAllStudentsWhoseNameHas_mmy () {
        //given
        studentRepository.saveAllAndFlush(
        Arrays.asList(
                new Student(null,"Sammy",12,"sammy@gmail.com",60),
                new Student(null,"Pio",17,"pio@gmail.com",50),
                new Student(null,"Tammy",15,"Tammy@gmail.com",30),
                new Student(null,"Pammy",11,"Pammy@gmail.com",90)
        ));
        //when
            Double avgMarks = studentRepository.getAvgMarksOfAllStudents();
        //then
        assertThat(avgMarks).isEqualTo(60l);


    }

}
