package com.example.taskmanager.mapper;

import com.example.taskmanager.dto.TaskCreationDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.entity.TaskEntity;
import com.example.taskmanager.enumeration.task.Status;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

  @Override
  public TaskEntity mapToEntity(TaskCreationDto dto) {
    return TaskEntity.builder()
        .name(dto.getName())
        .description(dto.getDescription())
        .status(Status.PENDING)
        .priority(dto.getPriority())
        .assignee(dto.getAssignee())
        .build();
  }

  @Override
  public TaskResponseDto mapToDto(TaskEntity entity) {
    return TaskResponseDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .description(entity.getDescription())
        .status(entity.getStatus())
        .priority(entity.getPriority())
        .assignee(entity.getAssignee())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }
}
