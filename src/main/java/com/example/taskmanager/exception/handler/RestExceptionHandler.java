package com.example.taskmanager.exception.handler;

import static com.example.taskmanager.exception.Error.CONSTRAINT_VIOLATION;

import com.example.taskmanager.exception.ApiError;
import com.example.taskmanager.exception.BaseException;
import com.example.taskmanager.exception.Error;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
    Error error = CONSTRAINT_VIOLATION;
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("errorCode", error.getCode());
    body.put("timestamp", Instant.now());
    body.put("status", error.getHttpStatus());
    body.put("message", error.getMessage());

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
    body.put("errors", errors);

    return ResponseEntity.badRequest().body(body);
  }


  @ExceptionHandler({BaseException.class})
  public ResponseEntity<ApiError> handleBase(BaseException ex) {
    Error error = ex.getError();
    ApiError apiError =
        new ApiError(error.getHttpStatus(), error.getCode(), Instant.now(), error.getMessage());

    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}
