package com.TaskManager.TaskManager.repository;

import com.TaskManager.TaskManager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}