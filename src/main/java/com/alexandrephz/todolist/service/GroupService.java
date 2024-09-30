package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.GroupAddMemberDto;
import com.alexandrephz.todolist.DTO.GroupRegistrationDto;
import com.alexandrephz.todolist.exceptions.GroupAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.GroupNotFoundException;
import com.alexandrephz.todolist.exceptions.GroupServiceLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface GroupService {
    ResponseEntity<ApiResponseDto<?>> createGroup(GroupRegistrationDto groupRegistrationDto)
        throws GroupAlreadyExistsException, GroupServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> addMember(GroupAddMemberDto groupAddMemberDto)
            throws GroupNotFoundException, GroupServiceLogicException;
}
