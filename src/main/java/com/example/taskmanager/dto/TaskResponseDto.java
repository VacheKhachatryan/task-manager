package com.example.taskmanager.dto;

import com.example.taskmanager.enumeration.task.Priority;
import com.example.taskmanager.enumeration.task.Status;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TaskResponseDto {

  private UUID id;

  private String name;

  private String description;

  private Status status;

  private Priority priority;

  private String assignee;

  private Instant createdAt;

  private Instant updatedAt;
}
