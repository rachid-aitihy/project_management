package com.project_management.demo.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

    private long id;
    private String projectName;
    private String projectDescription;
    private LocalDateTime createdDate;
    private List<TaskResponse> taskDTOS;
    private double progressPercentage;
    private int totalTasks;
    private int completedTasks;

}
