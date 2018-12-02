package com.example.QuartzScheduler.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiErrorsView {
    private List<ApiFieldError> apiFieldErrors;
    private List<ApiGlobalError> apiGlobalErrors;
}
