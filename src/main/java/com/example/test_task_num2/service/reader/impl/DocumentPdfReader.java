package com.example.test_task_num2.service.reader.impl;

import com.example.test_task_num2.service.reader.DocumentType;
import com.example.test_task_num2.service.reader.Reader;
import lombok.extern.log4j.Log4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Log4j
@Component
public class DocumentPdfReader<PDF> implements Reader<PDF> {

    @Override
    public DocumentType getType() {
        log.debug("Call method getType() for PDF");
        return DocumentType.PDF;
    }

    @Override
    public int countPages(File file) {
        log.debug("Call method countPages() with file = " + file);
        int pageCount = 0;
        try (PDDocument pdfDoc = PDDocument.load(file)) {
            pageCount = pdfDoc.getNumberOfPages();
        } catch (IOException e) {
            log.error("ERROR_MESSAGE_FOR_COUNT_PAGES_PDF_FILE", e);
            e.printStackTrace();
        }
        return pageCount;
    }
}
