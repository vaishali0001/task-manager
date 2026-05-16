package com.TaskManager.TaskManager.repository;

import com.TaskManager.TaskManager.entity.Task;
import com.TaskManager.TaskManager.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // FIX: was countByStatus(String) — TaskStatus is an enum, not a String
    long countByStatus(TaskStatus status);

    // Overdue = past due date AND not yet DONE
    @Query("SELECT COUNT(t) FROM Task t WHERE t.dueDate < :today AND t.status <> :doneStatus")
    long countOverdueTasks(@Param("today") LocalDate today,
                           @Param("doneStatus") TaskStatus doneStatus);

    List<Task> findByAssignedToId(Long userId);

    List<Task> findByProjectId(Long projectId);

    List<Task> findByStatus(TaskStatus status);
}
