package com.example.test_task_num2.factory;

import com.example.test_task_num2.service.reader.DocumentType;
import com.example.test_task_num2.service.reader.Reader;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Log4j
@Component
public class ReaderFactory {
    private static final Map<DocumentType, Reader> readerMap = new EnumMap<>(DocumentType.class);

    @Autowired
    private ReaderFactory(List<Reader> readers) {
        for(Reader reader : readers) {
            readerMap.put(reader.getType(), reader);
        }
    }

    public static <T> Reader<T> getReader(DocumentType documentType)  {
        log.debug("Call method getReader() with documentType = " + documentType);
        Reader<T> reader = readerMap.get(documentType);
        if (reader == null) {
            log.error("ERROR_MESSAGE_FOR_NULL_READER");
            throw new IllegalArgumentException();
        }
        return reader;
    }
}
