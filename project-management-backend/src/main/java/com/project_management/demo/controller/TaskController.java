package com.project_management.demo.controller;

import com.project_management.demo.DTO.TaskRequest;
import com.project_management.demo.DTO.TaskResponse;
import com.project_management.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {

        TaskResponse taskResponse = taskService.addTask(taskRequest);
        return ResponseEntity.ok(taskResponse);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<List<TaskResponse>> getTasksOfProject(@PathVariable Long projectId) {

        List<TaskResponse> taskResponses = this.taskService.getTasksOfProject(projectId);
        return new ResponseEntity< >(taskResponses, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> markAsCompleted(@PathVariable Long taskId) {
        TaskResponse taskResponse = taskService.markAsCompleted(taskId);
        return ResponseEntity.ok(taskResponse);
    }
}
