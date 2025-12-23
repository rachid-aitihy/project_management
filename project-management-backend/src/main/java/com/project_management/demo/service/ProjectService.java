package com.project_management.demo.service;

import com.project_management.demo.DTO.ProjectRequest;
import com.project_management.demo.DTO.ProjectResponse;
import com.project_management.demo.Mapper.ProjectMapper;
import com.project_management.demo.entity.Project;
import com.project_management.demo.entity.User;
import com.project_management.demo.repository.ProjectRepository;
import com.project_management.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserRepository userRepository;


    public ProjectResponse createProject(ProjectRequest projectRequest) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User authenticatedUser = this.userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));


        Project project = projectMapper.toProject(projectRequest);
        project.setCreatedDate(LocalDateTime.now());
        project.setUser(authenticatedUser);

        project =  projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }


    public List<ProjectResponse> getAllProjects() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User authenticatedUser = this.userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));

        List<Project> projects = projectRepository.findByUser_Id(authenticatedUser.getId());

        return projects.stream()
                .map(projectMapper::toProjectResponse)
                .toList();
    }

    public ProjectResponse viewProject(Long projectId) {


        Project project = projectRepository.findById(projectId).orElse(null);

        if (!isProjectOwner(project)) {
            throw new RuntimeException("Unauthorized: This project does not belong to you.");
        }

        return projectMapper.toProjectResponse(project);
    }

    private boolean isProjectOwner(Project project) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return project.getUser().getEmail().equals(email);
    }


}
