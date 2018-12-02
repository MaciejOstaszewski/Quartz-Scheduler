package com.example.QuartzScheduler.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiFieldError {
    private String field;
    private String code;
    private Object rejectedValue;
    private String message;
}
