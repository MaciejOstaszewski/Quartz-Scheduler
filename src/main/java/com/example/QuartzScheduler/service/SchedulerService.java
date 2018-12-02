package com.example.QuartzScheduler.service;

import com.example.QuartzScheduler.vm.TaskDTO;
import org.quartz.SchedulerException;

public interface SchedulerService {

    TaskDTO createTask(TaskDTO taskVm) throws SchedulerException;

    TaskDTO updateTask(TaskDTO taskVm) throws SchedulerException;

    void deleteTask(String jobName) throws SchedulerException;
}
