package com.project_management.demo.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    private Long taskId;
    private String taskName;
    private String taskDescription;
    private boolean isCompleted;
    private String projectName;
    private LocalDateTime taskDueDate;

}
