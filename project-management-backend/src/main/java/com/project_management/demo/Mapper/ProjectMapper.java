package com.project_management.demo.Mapper;

import com.project_management.demo.DTO.ProjectRequest;
import com.project_management.demo.DTO.ProjectResponse;
import com.project_management.demo.DTO.TaskResponse;
import com.project_management.demo.DTO.UserDTO;
import com.project_management.demo.entity.Project;
import com.project_management.demo.entity.Task;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProjectMapper {

    private TaskMapper taskMapper;
    private UserMapper userMapper;

    public Project toProject(ProjectRequest projectRequest) {

        return Project.builder()
                .projectName(projectRequest.getProjectName())
                .projectDescription(projectRequest.getProjectDescription())
                .build();
    }

    public Project toProject(ProjectResponse projectResponse) {

        List<Task> tasks = projectResponse.getTaskDTOS().stream()
                .map(taskResponse -> taskMapper.toTask(taskResponse))
                .toList();

        return Project.builder()
                .id(projectResponse.getId())
                .projectName(projectResponse.getProjectName())
                .projectDescription(projectResponse.getProjectDescription())
                .tasks(tasks)
                .build();
    }

    public ProjectResponse toProjectResponse(Project project) {

        List<Task> tasks = project.getTasks();
       ProjectResponse projectResponse = new ProjectResponse();
       projectResponse.setId(project.getId());
       projectResponse.setProjectName(project.getProjectName());
       projectResponse.setProjectDescription(project.getProjectDescription());
       projectResponse.setCreatedDate(project.getCreatedDate());
       if(tasks != null) {
           List<TaskResponse> taskResponseDTOS = tasks.stream().map(taskMapper::toTaskResponse).toList();

           projectResponse.setTaskDTOS(taskResponseDTOS);

           if (tasks == null || tasks.isEmpty()) {
               projectResponse.setTotalTasks(0);
               projectResponse.setCompletedTasks(0);
               projectResponse.setProgressPercentage(0.0);
           } else {
               int total = tasks.size();
               int completed = (int) tasks.stream().filter(Task::getIsCompleted).count();

               projectResponse.setTotalTasks(total);
               projectResponse.setCompletedTasks(completed);

               double percentage = (total == 0) ? 0 : ((double) completed / total) * 100;
               projectResponse.setProgressPercentage(percentage);
           }
       }

        return projectResponse;

    }

}


