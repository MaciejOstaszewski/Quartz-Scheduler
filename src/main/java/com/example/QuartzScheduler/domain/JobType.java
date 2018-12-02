package com.example.QuartzScheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.quartz.Job;

@Getter
@AllArgsConstructor
public enum JobType {
    TASK_1(ExampleJob.class);

    private Class<? extends Job> jobClass;
}
