package com.example.test_task_num2.controller;

import com.example.test_task_num2.exception.FilePathNotFoundException;
import com.example.test_task_num2.service.main.impl.MainServiceImpl;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j
@RestController
@RequestMapping("/api")
public class MainController {

    final MainServiceImpl mainServiceImpl;

    public MainController(MainServiceImpl mainServiceImpl) {
        this.mainServiceImpl = mainServiceImpl;
    }

    @GetMapping(value = "/filepath/**")
    public ResponseEntity<String> readDocuments(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        String filePath = requestURL.split("/filepath/")[1];
        if (!Files.exists(Paths.get(filePath))) {
            log.error("ERROR_MESSAGE_FOR_NOT_FOUND_FILE_PATH");
            throw new FilePathNotFoundException(filePath, "Could not found file path!");
        }
        JSONObject responseObj = mainServiceImpl.countPages(filePath);
        return ResponseEntity.ok().body(responseObj.toString());
    }
}
