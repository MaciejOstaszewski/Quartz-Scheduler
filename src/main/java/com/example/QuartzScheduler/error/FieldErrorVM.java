package com.example.QuartzScheduler.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FieldErrorVM {

    private final String objectName;
    private final String field;
    private final String message;

}
