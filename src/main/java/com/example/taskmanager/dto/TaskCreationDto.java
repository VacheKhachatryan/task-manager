package com.example.taskmanager.dto;

import com.example.taskmanager.enumeration.task.Priority;
import com.example.taskmanager.validator.NullOrNotBlank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TaskCreationDto {

  @NotNull
  @NotBlank
  private String name;

  @NotNull
  @NotBlank
  private String description;

  @NotNull
  private Priority priority;

  @NullOrNotBlank
  private String assignee;
}
