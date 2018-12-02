package com.example.QuartzScheduler.vm;

import com.example.QuartzScheduler.domain.JobType;
import com.example.QuartzScheduler.validators.OrderDate;
import com.example.QuartzScheduler.validators.ValidationCostants;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

@OrderDate
@Data
public class TaskDTO {
    private JobType type;
    private String name;
    private String description;
    private boolean active;

    @Pattern(regexp = ValidationCostants.SCHEDULER_CRON_REGEX, message = "{task.cron.pattern}")
    private String cron;

    private Date startTime;
    private Date endTime;
}
