package com.project_management.demo.DTO;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ProjectRequest {

    private String projectName;
    private String projectDescription;

}
