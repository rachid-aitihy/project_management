package com.project_management.demo.Mapper;

import com.project_management.demo.DTO.ProjectResponse;
import com.project_management.demo.DTO.UserDTO;
import com.project_management.demo.entity.Project;
import com.project_management.demo.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserMapper {


    public User toUser(UserDTO userDTO) {


        return User.builder()
                .email(userDTO.getEmail())
                .id(userDTO.getId())
                .fullName(userDTO.getUserName())
                .build();
    }

    public UserDTO toUserDTO(User user) {



        return UserDTO.builder()
                .id(user.getId())
                .userName(user.getFullName())
                .email(user.getEmail())
                .build();
    }
}
