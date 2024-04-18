package com.example.taskmanager.mapper;

import com.example.taskmanager.dto.TaskCreationDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.entity.TaskEntity;

public interface TaskMapper {

  TaskEntity mapToEntity(TaskCreationDto dto);

  TaskResponseDto mapToDto(TaskEntity entity);
}
