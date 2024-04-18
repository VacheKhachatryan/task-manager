package com.example.taskmanager.dto;

import com.example.taskmanager.enumeration.task.Priority;
import com.example.taskmanager.enumeration.task.Status;
import com.example.taskmanager.validator.NullOrNotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TaskUpdateDto {

  @NullOrNotBlank
  private String name;

  @NullOrNotBlank
  private String description;

  private Status status;

  private Priority priority;

  @NullOrNotBlank
  private String assignee;
}
