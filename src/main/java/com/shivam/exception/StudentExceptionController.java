package com.shivam.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class StudentExceptionController {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> handelStudentNotFoundException (RuntimeException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .status("Error")
                .description("Student Id was not found for the given ID !")
                .date(new Date())
                .errorCode("Not Found")
                .build();
        return new ResponseEntity<Object>(
                errorMessage , new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
