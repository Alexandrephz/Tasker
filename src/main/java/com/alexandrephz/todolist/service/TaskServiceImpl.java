package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.ApiResponseStatus;
import com.alexandrephz.todolist.DTO.TaskRegistrationDto;
import com.alexandrephz.todolist.DTO.TaskUpdateDto;
import com.alexandrephz.todolist.exceptions.TaskAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.TaskNotFoundException;
import com.alexandrephz.todolist.exceptions.TaskServiceLogicException;
import com.alexandrephz.todolist.model.Group;
import com.alexandrephz.todolist.model.Task;
import com.alexandrephz.todolist.model.TaskStatus;
import com.alexandrephz.todolist.repository.GroupRepository;
import com.alexandrephz.todolist.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public ResponseEntity<ApiResponseDto<?>> createTask(TaskRegistrationDto createTask)
            throws TaskAlreadyExistsException, TaskServiceLogicException {
        try {
            if (taskRepository.findByTaskTitle(createTask.getTaskTitle()) != null) {
                throw new TaskAlreadyExistsException("The task creation has failed: Already exists a task with same name" + createTask.getTaskTitle());
            }
            // Assuming you have a method to find the group by ID
            Optional<Group> optionalGroup = groupRepository.findById(createTask.getGroupId());
            if (optionalGroup.isEmpty()) {
                throw new TaskServiceLogicException("The specified group does not exist.");
            }

            Task newTask = new Task(createTask.getTaskTitle(), createTask.getTaskDescription(), createTask.getTaskEnd(), TaskStatus.CREATED, optionalGroup.get());
            taskRepository.save(newTask);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),"Tarefa criada com sucesso " + newTask));

        } catch (TaskAlreadyExistsException e) {
            throw new TaskAlreadyExistsException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllTasks() throws TaskNotFoundException, TaskServiceLogicException {
        List tasks = taskRepository.findAllByOrderByTaskStartedDesc();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), tasks));
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> findByTaskTitle(String task) throws TaskNotFoundException, TaskServiceLogicException {
       try {
           Task tasks = taskRepository.findByTaskTitle(task);
           System.out.println(tasks);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), tasks));
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updateTask(TaskUpdateDto updateTaskDetails) throws TaskNotFoundException, TaskServiceLogicException {
        try {
            Task task = taskRepository.getReferenceById(updateTaskDetails.getId());
            if ( task.getStatus() != TaskStatus.CANCELLED && task.getStatus() != TaskStatus.FINISHED){
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(),"Tarefa está com status que não permite alteração" + task));
            } else {
                objectMapper.updateValue(task, updateTaskDetails);
                taskRepository.save(task);
                //TODO implement user's assigned task
                //TODO implement group's assigned task
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),"Tarefa atualizada com sucesso " + task));
            }
        } catch (Exception e ){
            throw new TaskServiceLogicException(e.getMessage());
        }
    }
}
