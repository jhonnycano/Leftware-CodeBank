package com.leftware.todomanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leftware.todomanager.entities.Task;
import com.leftware.todomanager.entities.TaskLog;

public interface TaskLogRepository extends JpaRepository<TaskLog, String> {

    @Query("SELECT t FROM TaskLog t WHERE t.projectId = :projectId")
    public List<Task> findTaskLogsByProjectId(@Param("projectId") String projectId);

    @Query("SELECT t FROM TaskLog t WHERE t.taskId = :taskId")
    public List<Task> findTaskLogsByTaskId(@Param("taskId") String taskId);
}
