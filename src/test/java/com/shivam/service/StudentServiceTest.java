package com.shivam.service;

import com.shivam.dao.StudentRepository;
import com.shivam.exception.StudentNotFoundException;
import com.shivam.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@SpringBootTest
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Get all student service test")
    void getAllStudents() {
        service.getAllStudents();
        verify(studentRepository).findAll();
    }

    @Test
    void getSingleStudent() {
        //given
        Long id = 12l;
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        //when
        assertThrows(RuntimeException.class, () -> {
            service.getSingleStudent(id);
        });
        //then
        verify(studentRepository).findById(argumentCaptor.capture());
        assertThat(id).isEqualTo(argumentCaptor.getValue());
        assertThatThrownBy(()-> service.getSingleStudent(id)).isInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void addStudent() {
        //given
        Student student = new Student(null,"Sammy",7,"abc@gmail.com",89);
        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);

        //when
        service.addStudent(Arrays.asList(student));

        //then
        verify(studentRepository).saveAndFlush(argumentCaptor.capture());
        Student capturedStudent = argumentCaptor.getValue();
        assertThat(student).isEqualTo(capturedStudent);
    }

    @Test
    void editStudent() {
        //given
        Student student = new Student(null,"Sammy",7,"abc@gmail.com",89);
        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);

        //then
        assertThatThrownBy(() -> service.editStudent(student))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessage("Not found");
    }

    @Test
    void deleteStudent() {
        //given
        Long id = 12l;
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);

        //when
        service.deleteStudent(id);

        //then
        verify(studentRepository).deleteById(argumentCaptor.capture());
        assertThat(id).isEqualTo(argumentCaptor.getValue());


    }
}