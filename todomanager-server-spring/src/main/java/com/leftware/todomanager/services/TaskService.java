package com.leftware.todomanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.leftware.todomanager.models.Task;

@Service
public class TaskService {

    private final List<Task> tasks;

    public TaskService() {
        this.tasks = new ArrayList<>();
        this.tasks.add(new Task(UUID.randomUUID().toString(), "123", "Tarea", "Pending", null));
    }

    public List<Task> getTasksByProjectId(String projectId) {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
