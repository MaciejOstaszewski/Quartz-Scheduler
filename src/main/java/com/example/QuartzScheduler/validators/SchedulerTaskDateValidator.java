package com.example.QuartzScheduler.validators;


import com.example.QuartzScheduler.vm.TaskDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SchedulerTaskDateValidator implements ConstraintValidator<OrderDate, TaskDTO> {

    private OrderDate constraint;

    @Override
    public void initialize(OrderDate constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean isValid(TaskDTO taskDTO, ConstraintValidatorContext constraintValidatorContext) {
        return taskDTO.getStartTime().before(taskDTO.getEndTime());
    }


}
