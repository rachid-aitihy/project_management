package com.project_management.demo.controller;

import com.project_management.demo.DTO.ProjectRequest;
import com.project_management.demo.DTO.ProjectResponse;
import com.project_management.demo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest  projectRequest) {

        ProjectResponse projectResponse = projectService.createProject(projectRequest);

        return ResponseEntity.ok(projectResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        List<ProjectResponse> projectResponseList = projectService.getAllProjects();
        return ResponseEntity.ok(projectResponseList);
    }

    @GetMapping("/{idProject}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable long idProject) {

        ProjectResponse projectResponse = projectService.viewProject(idProject);
        return ResponseEntity.ok(projectResponse);
    }
}
