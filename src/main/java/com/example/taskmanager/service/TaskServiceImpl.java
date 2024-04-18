package com.example.taskmanager.service;

import static com.example.taskmanager.exception.Error.TASK_NOT_FOUND;

import com.example.taskmanager.dto.TaskCreationDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.dto.TaskUpdateDto;
import com.example.taskmanager.entity.TaskEntity;
import com.example.taskmanager.exception.RecordNotFoundException;
import com.example.taskmanager.mapper.TaskMapper;
import com.example.taskmanager.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final TaskRepository repository;
  private final TaskMapper mapper;


  @Override
  public TaskResponseDto findById(UUID id) {
    log.info("Attempt to find a task with id: {}", id);

    return repository.findById(id)
        .map(mapper::mapToDto)
        .orElseThrow(() -> new RecordNotFoundException(TASK_NOT_FOUND));
  }

  @Override
  public List<TaskResponseDto> findAll() {
    log.info("Finding all tasks");

    return repository.findAll().stream()
        .map(mapper::mapToDto)
        .toList();
  }

  @Override
  public TaskResponseDto create(TaskCreationDto dto) {
    TaskEntity entity = repository.saveAndFlush(mapper.mapToEntity(dto));
    log.info("The task (id: {}, name: {}) is successfully saved", entity.getId(), entity.getName());

    return mapper.mapToDto(entity);
  }

  @Override
  public TaskResponseDto updateById(UUID id, TaskUpdateDto dto) {
    log.info("Updating the task with id: {}", id);
    TaskEntity entity =  repository.findById(id)
        .orElseThrow(() -> new RecordNotFoundException(TASK_NOT_FOUND));
    update(entity, dto);
    TaskEntity savedEntity = repository.save(entity);
    log.info("The task with id: {} is successfully updated", id);

    return mapper.mapToDto(savedEntity);
  }

  @Override
  public TaskResponseDto deleteById(UUID id) {
    log.info("Attempt to delete the task with id: {}", id);
    TaskEntity entity = repository.findById(id)
        .orElseThrow(() -> new RecordNotFoundException(TASK_NOT_FOUND));
    repository.delete(entity);

    return mapper.mapToDto(entity);
  }

  private void update(TaskEntity entity, TaskUpdateDto dto) {
    Optional.ofNullable(dto.getName()).ifPresent(entity::setName);
    Optional.ofNullable(dto.getDescription()).ifPresent(entity::setDescription);
    Optional.ofNullable(dto.getStatus()).ifPresent(entity::setStatus);
    Optional.ofNullable(dto.getPriority()).ifPresent(entity::setPriority);
    Optional.ofNullable(dto.getAssignee()).ifPresent(entity::setAssignee);
  }
}
