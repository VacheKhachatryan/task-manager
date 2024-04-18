package com.example.taskmanager.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.example.taskmanager.dto.TaskCreationDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.dto.TaskUpdateDto;
import com.example.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService service;

  @GetMapping("/{id}")
  public ResponseEntity<TaskResponseDto> findById(@PathVariable UUID id) {
    return ResponseEntity.ok(service.findById(id));
  }

  @GetMapping
  public ResponseEntity<List<TaskResponseDto>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @PostMapping
  public ResponseEntity<TaskResponseDto> create(@Valid @RequestBody TaskCreationDto dto) {
    return new ResponseEntity<>(service.create(dto), CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskResponseDto> updateById(@PathVariable UUID id,
      @Valid @RequestBody TaskUpdateDto dto) {
    return ResponseEntity.ok(service.updateById(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<TaskResponseDto> deleteById(@PathVariable UUID id) {
    return ResponseEntity.ok(service.deleteById(id));
  }
}
