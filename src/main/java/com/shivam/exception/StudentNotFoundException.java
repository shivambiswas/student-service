package com.shivam.exception;

import lombok.Getter;

@Getter
public class StudentNotFoundException extends RuntimeException{

    private String errorCode;
    private String description;
    public StudentNotFoundException(String errorCode, String description) {
        super(description);
        this.errorCode = errorCode;
        this.description = description;
    }
}
