package com.example.QuartzScheduler.controller;


import com.example.QuartzScheduler.service.SchedulerService;
import com.example.QuartzScheduler.vm.TaskDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/schedule")
public class SchedulerController {


    private final SchedulerService schedulerService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) throws SchedulerException {
        return new ResponseEntity<>(schedulerService.createTask(taskDTO), HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<TaskDTO> updateTask(@Valid @RequestBody TaskDTO taskDTO) throws SchedulerException {
        return new ResponseEntity<>(schedulerService.updateTask(taskDTO), HttpStatus.OK);
    }


    @DeleteMapping("/{job}")
    public ResponseEntity<Void> deleteTask(@PathVariable String job) throws SchedulerException {
        schedulerService.deleteTask(job);
        return ResponseEntity.ok().build();
    }

}
