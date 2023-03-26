package com.example.test_task_num2.service.main.impl;

import com.example.test_task_num2.factory.ReaderFactory;
import com.example.test_task_num2.service.main.MainService;
import com.example.test_task_num2.service.reader.DocumentType;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.tika.Tika;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

@Log4j
@Service
public class MainServiceImpl implements MainService {

    private final Tika defaultTika = new Tika();

    @Override
    public JSONObject countPages(String filePath) {
        log.debug("Call method countPages() with filePath = " + filePath);
        File directory = new File(filePath);
        int documentCount = 0;
        int pageCount = 0;
        Collection<File> files = FileUtils.listFiles(
                directory,
                new RegexFileFilter("^(.*?)"),
                DirectoryFileFilter.DIRECTORY
        );
        for (File file : files) {
            String fileType = identifyFileType(file);
            switch (fileType) {
                case "application/pdf":
                    //PDF File
                    pageCount += ReaderFactory.getReader(DocumentType.PDF).countPages(file);
                    documentCount++;
                    break;
                case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                    //Word File
                    pageCount += ReaderFactory.getReader(DocumentType.DOCX).countPages(file);
                    documentCount++;
                    break;
                default:
                    log.debug("It's file or directory: " + file);
                    break;
            }
        }
        JSONObject responseObj = new JSONObject();
        responseObj.put("Documents", documentCount);
        responseObj.put("Pages", pageCount);
        return responseObj;
    }

    @Override
    public String identifyFileType(File file) {
        log.debug("Call method identifyFileType() with file = " + file);
        String fileType;
        try {
            fileType = defaultTika.detect(file);
        } catch (IOException e) {
            log.error("ERROR_MESSAGE_FOR_IDENTITY_FILE", e);
            System.out.println("Unable to detect type of file " + file.getName() + " - " + e);
            fileType = "Unknown";
        }
        return fileType;
    }
}
