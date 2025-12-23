package com.project_management.demo.service;

import com.project_management.demo.DTO.TaskRequest;
import com.project_management.demo.DTO.TaskResponse;
import com.project_management.demo.Mapper.TaskMapper;
import com.project_management.demo.entity.Project;
import com.project_management.demo.entity.Task;
import com.project_management.demo.repository.ProjectRepository;
import com.project_management.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectRepository projectRepository;

    public TaskResponse addTask(TaskRequest taskRequest) {
        Project project = projectRepository.findById(taskRequest.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!isProjectOwner(project)) {
            throw new RuntimeException("Unauthorized: You cannot add tasks to a project you don't own.");
        }

        Task task = taskMapper.toTask(taskRequest);
        task.setProject(project);
        task.setIsCompleted(false);

        return taskMapper.toTaskResponse(taskRepository.save(task));
    }

    public List<TaskResponse> getTasksOfProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!isProjectOwner(project)) {
            throw new RuntimeException("Unauthorized access to this project.");
        }

        return project.getTasks().stream()
                .map(taskMapper::toTaskResponse)
                .toList();
    }

    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!isProjectOwner(task.getProject())) {
            throw new RuntimeException("Unauthorized: You do not own this task.");
        }

        taskRepository.delete(task);
    }

    public TaskResponse markAsCompleted(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!isProjectOwner(task.getProject())) {
            throw new RuntimeException("Unauthorized: You do not own this task.");
        }

        task.setIsCompleted(true);
        return taskMapper.toTaskResponse(taskRepository.save(task));
    }

   private boolean isProjectOwner(Project project) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return project.getUser().getEmail().equals(currentUserEmail);
    }
}