package com.example.test_task_num2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilePathNotFoundException extends RuntimeException {
    private String filePath;
    private String message;
}
