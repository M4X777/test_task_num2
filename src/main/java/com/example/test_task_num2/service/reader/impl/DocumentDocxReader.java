package com.example.test_task_num2.service.reader.impl;

import com.example.test_task_num2.service.reader.DocumentType;
import com.example.test_task_num2.service.reader.Reader;
import lombok.extern.log4j.Log4j;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Log4j
@Component
public class DocumentDocxReader<DOCX> implements Reader<DOCX> {

    @Override
    public DocumentType getType() {
        log.debug("Call method getType() for DOCX");
        return DocumentType.DOCX;
    }

    @Override
    public int countPages(File file) {
        log.debug("Call method countPages() with file = " + file);
        int pageCount = 0;
        try (XWPFDocument docx = new XWPFDocument(POIXMLDocument.openPackage(file.getAbsolutePath()))) {
            pageCount = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
        } catch (IOException e) {
            log.error("ERROR_MESSAGE_FOR_COUNT_PAGES_DOCX_FILE", e);
            throw new RuntimeException(e);
        }
        return pageCount;
    }
}
