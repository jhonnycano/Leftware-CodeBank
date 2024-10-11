package com.leftware.todomanager.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.leftware.todomanager.models.Task;

@Service
public class TaskService {

    private final List<Task> tasks;

    public TaskService() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasksByProjectId(String projectId) {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
