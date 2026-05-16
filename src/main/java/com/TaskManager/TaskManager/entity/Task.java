package com.TaskManager.TaskManager.entity;

import com.TaskManager.TaskManager.enums.Priority;
import com.TaskManager.TaskManager.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate dueDate;

    // Hide password, and stop project->tasks->project recursion
    @ManyToOne
    @JsonIgnoreProperties({"tasks", "password", "createdBy"})
    private Project project;

    // Hide password from assignedTo user
    @ManyToOne
    @JsonIgnoreProperties({"password"})
    private User assignedTo;
}
