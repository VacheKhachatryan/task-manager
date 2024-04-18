package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskCreationDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.dto.TaskUpdateDto;
import java.util.List;
import java.util.UUID;

public interface TaskService {

  TaskResponseDto findById(UUID id);

  List<TaskResponseDto> findAll();

  TaskResponseDto create(TaskCreationDto dto);

  TaskResponseDto updateById(UUID id, TaskUpdateDto dto);

  TaskResponseDto deleteById(UUID id);
}
