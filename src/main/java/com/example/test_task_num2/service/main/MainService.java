package com.example.test_task_num2.service.main;

import org.json.JSONObject;
import java.io.File;

public interface MainService {

    JSONObject countPages(String filePath);

    String identifyFileType(File file);
}
