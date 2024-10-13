package com.leftware.todomanager.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.leftware.todomanager.common.Constants;
import com.leftware.todomanager.entities.Task;
import com.leftware.todomanager.entities.TaskLog;
import com.leftware.todomanager.models.TaskModel;
import com.leftware.todomanager.repositories.TaskLogRepository;
import com.leftware.todomanager.repositories.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskLogRepository taskLogRepository;

    public TaskService(
            TaskRepository taskRepository,
            TaskLogRepository taskLogRepository
    ) {
        this.taskRepository = taskRepository;
        this.taskLogRepository = taskLogRepository;
    }

    public List<TaskModel> getTasksByProjectId(String projectId) {
        var tasks = taskRepository.findTasksByProjectId(projectId);
        return tasks.stream().map(this::convertToTaskModel).collect(Collectors.toList());
    }

    public TaskModel getTaskById(String projectId, String taskId) {
        TaskModel task = convertToTaskModel(taskRepository.findById(taskId).orElse(null));
        return task;
    }

    public void addTask(TaskModel taskModel) {
        Task task = convertToTask(taskModel);
        taskRepository.save(task);

        addTaskLog(task, "");
    }

    public String changeStatus(String projectId, String taskId, String status) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            return "Task not found";
        }

        if (!Constants.STATUSES.contains(status)) {
            return "Invalid status";
        }

        String oldStatus = task.getStatus();

        LocalDateTime completedAt = "Done".equals(status)
                ? LocalDateTime.now()
                : null;

        task.setStatus(status);
        task.setCompletedAt(completedAt);
        taskRepository.save(task);

        addTaskLog(task, oldStatus);
        return null;
    }

    private void addTaskLog(Task task, String oldStatus) {
        TaskLog taskLog = new TaskLog();
        taskLog.setId(UUID.randomUUID().toString());
        taskLog.setProjectId(task.getProjectId());
        taskLog.setTaskId(task.getId());
        taskLog.setOldStatus(oldStatus);
        taskLog.setNewStatus(task.getStatus());
        taskLog.setCreatedAt(LocalDateTime.now());
        taskLogRepository.save(taskLog);
    }

    private Task convertToTask(TaskModel taskModel) {
        if (taskModel == null) {
            return null;
        }
        Task task = new Task();
        task.setId(taskModel.getId());
        task.setProjectId(taskModel.getProjectId());
        task.setText(taskModel.getText());
        task.setStatus(taskModel.getStatus());
        task.setCompletedAt(taskModel.getCompletedAt());

        return task;
    }

    private TaskModel convertToTaskModel(Task task) {
        if (task == null) {
            return null;
        }
        TaskModel taskModel = new TaskModel();
        taskModel.setId(task.getId());
        taskModel.setProjectId(task.getProjectId());
        taskModel.setText(task.getText());
        taskModel.setStatus(task.getStatus());
        taskModel.setCompletedAt(task.getCompletedAt());

        return taskModel;
    }
}
