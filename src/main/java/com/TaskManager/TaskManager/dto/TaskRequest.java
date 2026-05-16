package com.TaskManager.TaskManager.dto;

import com.TaskManager.TaskManager.enums.Priority;
import com.TaskManager.TaskManager.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {

    @NotBlank(message = "Task title is required")
    private String title;

    private String description;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    @NotNull(message = "Priority is required")
    private Priority priority;

    private LocalDate dueDate;

    private Long assignedToId;

    @NotNull(message = "Project ID is required")
    private Long projectId;
}
