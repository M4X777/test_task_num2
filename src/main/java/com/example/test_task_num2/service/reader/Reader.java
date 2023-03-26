package com.example.test_task_num2.service.reader;

import java.io.File;

public interface Reader<T> {
    DocumentType getType();
    int countPages(File file);
}
