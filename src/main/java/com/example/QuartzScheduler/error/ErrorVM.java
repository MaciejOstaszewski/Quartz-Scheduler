package com.example.QuartzScheduler.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
@AllArgsConstructor
public class ErrorVM implements Serializable {

    private final String message;
    private final String description;
    private List<FieldErrorVM> fieldErrors;

    public ErrorVM(String message) {
        this(message, null);
    }

    public ErrorVM(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public void add(String objectName, String field, String message) {
        if (isNull(fieldErrors)) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorVM(objectName, field, message));
    }

}
