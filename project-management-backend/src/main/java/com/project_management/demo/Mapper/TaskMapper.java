package com.project_management.demo.Mapper;

import com.project_management.demo.DTO.TaskRequest;
import com.project_management.demo.DTO.TaskResponse;
import com.project_management.demo.entity.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaskMapper {

    public Task toTask(TaskRequest taskRequest) {
        return Task.builder()
                .taskName(taskRequest.getTaskName())
                .taskDescription(taskRequest.getTaskDescription())
                .taskDueDate(taskRequest.getTaskDueDate())
                .build();
    }

    public Task toTask(TaskResponse taskResponse) {
        return Task.builder()
                .taskName(taskResponse.getTaskName())
                .taskDescription(taskResponse.getTaskDescription())
                .taskDueDate(taskResponse.getTaskDueDate())
                .id(taskResponse.getTaskId())
                .isCompleted(taskResponse.isCompleted())
                .build();
    }


    public TaskResponse toTaskResponse(Task task) {
        return TaskResponse.builder()
                .taskId(task.getId())
                .taskName(task.getTaskName())
                .taskDescription(task.getTaskDescription())
                .isCompleted(task.getIsCompleted())
                .taskDueDate(task.getTaskDueDate())
                .projectName(task.getProject().getProjectName())
                .build();
    }
}
