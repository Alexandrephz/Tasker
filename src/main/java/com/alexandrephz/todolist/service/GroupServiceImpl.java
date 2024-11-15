package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.ApiResponseStatus;
import com.alexandrephz.todolist.DTO.GroupAddMemberDto;
import com.alexandrephz.todolist.DTO.GroupRegistrationDto;
import com.alexandrephz.todolist.exceptions.GroupAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.GroupNotFoundException;
import com.alexandrephz.todolist.exceptions.GroupServiceLogicException;
import com.alexandrephz.todolist.exceptions.UserAlreadyExistsException;
import com.alexandrephz.todolist.model.Group;
import com.alexandrephz.todolist.model.User;
import com.alexandrephz.todolist.model.UserRole;
import com.alexandrephz.todolist.repository.GroupRepository;
import com.alexandrephz.todolist.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@Slf4j
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> createGroup(GroupRegistrationDto groupRegistrationDto) throws GroupAlreadyExistsException, GroupServiceLogicException {
        try {
            if (groupRegistrationDto.getUserRole() != UserRole.ADMIN) {
                throw new GroupServiceLogicException("Voce não tem permissão.");
            }
            if (groupRepository.findByGroupName(groupRegistrationDto.getGroupName()) != null) {
                throw new GroupAlreadyExistsException("O nome do grupo ja existe.");
            }
            Group group = new Group(groupRegistrationDto.getGroupName(), groupRegistrationDto.getGroupDescription());
            groupRepository.save(group);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Grupo criado com sucesso " + group));
        } catch (Exception e) {
            throw new GroupServiceLogicException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> addMember(GroupAddMemberDto groupAddMemberDto) throws GroupNotFoundException, GroupServiceLogicException {

        return null;
    }
}