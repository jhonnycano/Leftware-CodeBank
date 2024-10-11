package com.leftware.todomanager.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.leftware.todomanager.models.Task;

@Service
public class TaskService {

    private final List<Task> tasks;

    public TaskService() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("asd1", "asd1", "task 1", "Pending", null));
        taskList.add(new Task("asd2", "asd1", "task 2", "Completed", LocalDateTime.now()));
        taskList.add(new Task("asd2", "asd2", "task 3", "In Progress", null));
        this.tasks = taskList;
    }

    public List<Task> getTasksByProjectId(String projectId) {
        return tasks;
    }

}
