package com.alexandrephz.todolist.controller;

import com.alexandrephz.todolist.DTO.*;
import com.alexandrephz.todolist.exceptions.TaskAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.TaskNotFoundException;
import com.alexandrephz.todolist.exceptions.TaskServiceLogicException;
import com.alexandrephz.todolist.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {
    @Autowired
    GroupService groupService;

    @PostMapping("/group/new")
    public ResponseEntity<ApiResponseDto<?>> createGroup(@RequestBody GroupRegistrationDto groupRegistrationDto) throws TaskAlreadyExistsException, TaskServiceLogicException {
        return groupService.createGroup(groupRegistrationDto);
    }

    @PatchMapping("/group/{UUID}")
    public ResponseEntity<ApiResponseDto<?>> addMember(@RequestBody GroupAddMemberDto groupAddMemberDto)
            throws TaskServiceLogicException, TaskNotFoundException {
        return groupService.addMember(groupAddMemberDto);
    }

}
