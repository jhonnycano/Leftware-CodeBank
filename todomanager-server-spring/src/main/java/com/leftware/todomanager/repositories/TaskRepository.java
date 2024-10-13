package com.leftware.todomanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leftware.todomanager.entities.Task;

public interface TaskRepository extends JpaRepository<Task, String> {

    @Query("SELECT t FROM Task t WHERE t.projectId = :projectId")
    public List<Task> findTasksByProjectId(@Param("projectId") String projectId);
}
