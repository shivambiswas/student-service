package com.shivam.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ErrorMessage {

    private String status;
    private Date date;
    private String errorCode;
    private String description;
}
