package com.alexandrephz.todolist.DTO;

import com.alexandrephz.todolist.model.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GroupAddMemberDto {
    private UUID groupId;
    private Set<UUID> membersIds;
    private GroupRole groupRole;
}

