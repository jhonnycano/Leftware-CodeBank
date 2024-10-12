package com.leftware.todomanager.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.leftware.todomanager.models.Task;

@Service
public class TaskService {

    private final List<Task> tasks;
    private final List<String> statuses;

    public TaskService() {
        this.tasks = new ArrayList<>();
        this.tasks.add(new Task(UUID.randomUUID().toString(), "123", "Tarea", "Pending", null));
        this.statuses = Arrays.asList("Pending", "In Progress", "Done");
    }

    public List<Task> getTasksByProjectId(String projectId) {
        return tasks;
    }

    public Task getTaskById(String projectId, String taskId) {
        Task task = tasks.stream()
                .filter(p -> (p.getProjectId().equals(projectId)) && p.getId().equals(taskId))
                .findFirst()
                .orElse(null);
        return task;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String changeStatus(String projectId, String taskId, String status) {
        Task task = getTaskById(projectId, taskId);
        if (task == null) {
            return "Task not found";
        }

        if (!statuses.contains(status)) {
            return "Invalid status";
        }

        task.setStatus(status);
        return null;
    }
}
