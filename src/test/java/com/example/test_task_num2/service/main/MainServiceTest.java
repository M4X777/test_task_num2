package com.example.test_task_num2.service.main;
import com.example.test_task_num2.service.main.impl.MainServiceImpl;
import com.example.test_task_num2.service.reader.impl.DocumentDocxReader;
import com.example.test_task_num2.service.reader.impl.DocumentPdfReader;
import org.apache.tika.Tika;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class MainServiceTest {

    private final ClassLoader classloader = Thread.currentThread().getContextClassLoader();

    @Mock
    private MainServiceImpl mainServiceImpl;

    @Test
    @DisplayName("Test that counts the number of pages in a .docx file")
    void testCountDOCXPages() {
        File file = new File(classloader.getResource("test_filepath/TEST.docx").getFile());
        assertEquals(new DocumentDocxReader<>().countPages(file), 3);
    }

    @Test
    @DisplayName("Test that counts the number of pages in a .pdf file")
    void testCountPDFPages() {
        File file = new File(classloader.getResource("test_filepath/TEST.pdf").getFile());
        assertEquals(new DocumentPdfReader<>().countPages(file), 3);
    }

    @Test
    @DisplayName("Test that identifies the .pdf file format")
    void testIdentifyPDFFileType() throws IOException {
        File file = new File(classloader.getResource("test_filepath/TEST.pdf").getFile());
        assertEquals(new Tika().detect(file), "application/pdf");
    }

    @Test
    @DisplayName("Test that identifies the .docx file format")
    void testIdentifyDOCXFileType() throws IOException {
        File file = new File(classloader.getResource("test_filepath/TEST.docx").getFile());
        assertEquals(new Tika().detect(file), "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
    }

    @Test
    @DisplayName("Test that counts the total number of files and pages")
    void testCountTotalNumberOfFilesAndPages() {
        String dirPath = "src/test/resources/test_filepath";
        JSONObject expectedResponse = new JSONObject("{\"Documents\":\"2\",\"Pages\":\"6\"}");

        Mockito.when(mainServiceImpl.countPages(dirPath)).thenReturn(expectedResponse);
        JSONObject actualResponse = mainServiceImpl.countPages(dirPath);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Test that counts files with incorrect format")
    void testCountFilesWithIncorrectFormat() {
        String dirPath = "src/test/resources/incorrect_files";
        JSONObject expectedResponse = new JSONObject("{\"Documents\":\"0\",\"Pages\":\"0\"}");
        Mockito.when(mainServiceImpl.countPages(dirPath)).thenReturn(expectedResponse);
        JSONObject actualResponse = mainServiceImpl.countPages(dirPath);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Test that processes an empty directory")
    void testProcessEmptyDirectory() {
        String dirPath = "src/test/resources/empty_dir";
        JSONObject expectedResponse = new JSONObject("{\"Documents\":\"0\",\"Pages\":\"0\"}");

        Mockito.when(mainServiceImpl.countPages(dirPath)).thenReturn(expectedResponse);
        JSONObject actualResponse = mainServiceImpl.countPages(dirPath);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Test that processes the directory tree")
    void testProcessDirectoryTree() {
        String dirPath = "src/test/resources/tree_of_dirs";
        JSONObject expectedResponse = new JSONObject("{\"Documents\":\"3\",\"Pages\":\"9\"}");

        Mockito.when(mainServiceImpl.countPages(dirPath)).thenReturn(expectedResponse);
        JSONObject actualResponse = mainServiceImpl.countPages(dirPath);

        assertEquals(expectedResponse, actualResponse);
    }
}
