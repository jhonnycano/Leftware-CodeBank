package com.leftware.todomanager.entities;

import java.time.LocalDateTime;

import com.leftware.todomanager.common.LocalDateTimeAttributeConverter;

import jakarta.persistence.*;

@Entity
public class Task {

    @Id
    private String id;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "text")
    private String text;

    @Column(name = "status")
    private String status;

    @Column(name = "completed_at", columnDefinition = "TIMESTAMP")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime completedAt;

    public Task() {
    }

    public Task(String id, String projectId, String text, String status, LocalDateTime completedAt) {
        this.id = id;
        this.projectId = projectId;
        this.text = text;
        this.status = status;
        this.completedAt = completedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
