package com.TaskManager.TaskManager.controller;

import com.TaskManager.TaskManager.enums.TaskStatus;
import com.TaskManager.TaskManager.repository.ProjectRepository;
import com.TaskManager.TaskManager.repository.TaskRepository;
import com.TaskManager.TaskManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DashboardController {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        Map<String, Long> stats = new HashMap<>();

        stats.put("totalProjects",    projectRepository.count());
        stats.put("totalTasks",       taskRepository.count());
        stats.put("totalMembers",     userRepository.count());

        // FIX: use enum, not String — original code was broken
        stats.put("completedTasks",   taskRepository.countByStatus(TaskStatus.DONE));
        stats.put("pendingTasks",     taskRepository.countByStatus(TaskStatus.TODO));
        stats.put("inProgressTasks",  taskRepository.countByStatus(TaskStatus.IN_PROGRESS));

        // NEW: overdue = past due date and not yet DONE
        stats.put("overdueTasks",
                taskRepository.countOverdueTasks(LocalDate.now(), TaskStatus.DONE));

        return stats;
    }
}
