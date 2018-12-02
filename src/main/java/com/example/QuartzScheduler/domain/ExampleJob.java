package com.example.QuartzScheduler.domain;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class ExampleJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("TASK 1 EXECUTED");
    }
}
