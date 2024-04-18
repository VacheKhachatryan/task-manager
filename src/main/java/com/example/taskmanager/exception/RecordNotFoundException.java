package com.example.taskmanager.exception;

public class RecordNotFoundException extends BaseException {

  public RecordNotFoundException(Error error) {
    super(error);
  }
}