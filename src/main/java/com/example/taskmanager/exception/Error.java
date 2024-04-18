package com.example.taskmanager.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Error {
  CONSTRAINT_VIOLATION(4001, BAD_REQUEST, "There is an invalid value in user input"),

  TASK_NOT_FOUND(4041, NOT_FOUND, "There is no task found with such id");

  private final Integer code;
  private final HttpStatus httpStatus;
  private final String message;
}
