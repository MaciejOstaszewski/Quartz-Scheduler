package com.example.QuartzScheduler.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiGlobalError {
    private String code;
    private String message;
}
