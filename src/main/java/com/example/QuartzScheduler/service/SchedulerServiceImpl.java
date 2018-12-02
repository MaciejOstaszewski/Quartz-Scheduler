package com.example.QuartzScheduler.service;

import com.example.QuartzScheduler.vm.TaskDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.TimeZone;

import static com.example.QuartzScheduler.dateutils.DateUtils.getUTCZonedDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final SchedulerFactoryBean schedulerFactory;

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) throws SchedulerException {
        log.info("Schedule job " + taskDTO.getName());
        if (getScheduler().checkExists(new JobKey(taskDTO.getName()))) {
            throw new SchedulerException("Task with name: " + taskDTO.getName() + " already exist");
        }
        changeDatesTimeZone(taskDTO);
        getScheduler().scheduleJob(getJobDetail(taskDTO), getTrigger(taskDTO));
        if (!taskDTO.isActive()) {
            getScheduler().pauseJob(JobKey.jobKey(taskDTO.getName()));
        }
        return taskDTO;
    }

    @Override
    public TaskDTO updateTask(TaskDTO taskDTO) throws SchedulerException {
        if (!getScheduler().checkExists(new JobKey(taskDTO.getName()))) {
            throw new SchedulerException("Task with name: " + taskDTO.getName() + " does not exist");
        } else {
            getScheduler().deleteJob(new JobKey(taskDTO.getName()));
            changeDatesTimeZone(taskDTO);
            getScheduler().scheduleJob(getJobDetail(taskDTO), getTrigger(taskDTO));

            log.info("Task with name: " + taskDTO.getName() + " updated");
            if (!taskDTO.isActive())
                getScheduler().pauseJob(JobKey.jobKey(taskDTO.getName()));
            else
                getScheduler().resumeJob(JobKey.jobKey(taskDTO.getName()));
        }
        return taskDTO;
    }

    @Override
    public void deleteTask(String jobName) throws SchedulerException {
        if (!getScheduler().checkExists(new JobKey(jobName))) {
            throw new SchedulerException("Task with name: " + jobName + " does not exist");
        } else {
            getScheduler().deleteJob(new JobKey(jobName));
            log.info("Task with name: " + jobName + " unscheduled");
        }

    }

    private JobDetail getJobDetail(TaskDTO taskDTO) {
        return newJob(taskDTO.getType().getJobClass())
                .withIdentity(taskDTO.getName())
                .withDescription(taskDTO.getDescription())
                .build();
    }

    private Trigger getTrigger(TaskDTO taskVm) {
        return newTrigger()
                .withIdentity(taskVm.getName() + "_trigger")
                .startAt(taskVm.getStartTime())
                .endAt(taskVm.getEndTime())
                .withSchedule(CronScheduleBuilder.cronSchedule(taskVm.getCron()).inTimeZone(TimeZone.getTimeZone("Poland")))
                .forJob(taskVm.getName())
                .build();
    }

    private void changeDatesTimeZone(TaskDTO taskVm) {
        taskVm.setStartTime(getUTCZonedDate(taskVm.getStartTime()));
        taskVm.setEndTime(getUTCZonedDate(taskVm.getEndTime()));
    }

    private Scheduler getScheduler() {
        return schedulerFactory.getScheduler();
    }
}
