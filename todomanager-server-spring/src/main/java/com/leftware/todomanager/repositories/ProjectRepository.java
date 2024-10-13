package com.leftware.todomanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leftware.todomanager.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {
}