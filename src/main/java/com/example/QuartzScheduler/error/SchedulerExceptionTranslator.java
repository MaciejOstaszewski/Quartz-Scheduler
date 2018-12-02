package com.example.QuartzScheduler.error;

import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SchedulerExceptionTranslator extends ExceptionTranslator {

    @ResponseBody
    @ExceptionHandler({SchedulerException.class})
    public ResponseEntity handleSchedulerError(SchedulerException ex) {
        logError("Scheduler Exception", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorVM("Scheduler Exception", ex.getLocalizedMessage()));
    }
}
