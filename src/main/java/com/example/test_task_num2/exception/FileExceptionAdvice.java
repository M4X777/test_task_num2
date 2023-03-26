package com.example.test_task_num2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class FileExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(FilePathNotFoundException.class)
    public ResponseEntity<Object> handleFileNotFoundException(FilePathNotFoundException e) {
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("filepath", e.getFilePath());
        responseBody.put("message",e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
