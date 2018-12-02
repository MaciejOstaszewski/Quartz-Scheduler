package com.example.QuartzScheduler.scheduler;


import com.example.QuartzScheduler.domain.JobType;
import com.example.QuartzScheduler.service.SchedulerService;
import com.example.QuartzScheduler.vm.TaskDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SchedulerControllerIntTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper json;

    @Autowired
    private SchedulerService schedulerService;

    private TaskDTO createTask() {
        TaskDTO taskVm = new TaskDTO();
        taskVm.setName("job");
        taskVm.setCron("* * * ? * *");
        taskVm.setDescription("description");
        taskVm.setType(JobType.TASK_1);
        taskVm.setActive(true);
        taskVm.setStartTime(Date.from(Instant.now().plus(1, ChronoUnit.MINUTES)));
        taskVm.setEndTime(Date.from(Instant.now().plus(3, ChronoUnit.MINUTES)));

        return taskVm;
    }


    @Test
    public void shouldCreateTask() throws Exception {
        mockMvc.perform(post("/api/schedule")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.writeValueAsString(createTask())))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        TaskDTO taskVm = createTask();
        schedulerService.createTask(taskVm);
        taskVm.setDescription("desc2");
        mockMvc.perform(put("/api/schedule")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.writeValueAsString(taskVm)))
                .andExpect(jsonPath("$.description").value("desc2"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        TaskDTO taskVm = createTask();
        schedulerService.createTask(taskVm);
        mockMvc.perform(delete("/api/schedule/{job}", "job")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldNotCreateTaskWithWrongCrone() throws Exception {
        TaskDTO taskVm = new TaskDTO();
        taskVm.setName("job");
        taskVm.setCron("* * F ? * *");
        taskVm.setDescription("description");
        taskVm.setType(JobType.TASK_1);
        taskVm.setActive(true);
        taskVm.setStartTime(Date.from(Instant.now().plus(1, ChronoUnit.MINUTES)));
        taskVm.setEndTime(Date.from(Instant.now().plus(3, ChronoUnit.MINUTES)));
        mockMvc.perform(post("/api/schedule")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.writeValueAsString(taskVm)))
                .andExpect(status().is(422))
                .andDo(print());
    }

    @Test
    public void shouldNotCreateTaskWithWrongDate() throws Exception {
        TaskDTO taskVm = new TaskDTO();
        taskVm.setName("job");
        taskVm.setCron("* * * ? * *");
        taskVm.setDescription("description");
        taskVm.setType(JobType.TASK_1);
        taskVm.setActive(true);
        taskVm.setStartTime(Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)));
        taskVm.setEndTime(Date.from(Instant.now().plus(3, ChronoUnit.MINUTES)));
        mockMvc.perform(post("/api/schedule")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.writeValueAsString(taskVm)))
                .andExpect(status().is(422))
                .andDo(print());
    }
}
