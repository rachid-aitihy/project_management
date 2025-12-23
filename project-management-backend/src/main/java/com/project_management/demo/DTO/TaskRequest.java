package com.project_management.demo.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    private String taskName;
    private String taskDescription;
    private LocalDateTime taskDueDate;
    private Long projectId;

}
