package com.example.springlearning.entity.file;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileReader {
    public FileReader() {
        LOGGER.info("FileReader constructor");
    }

    // 充当init method
    public void init() {
        LOGGER.info("FileReader init-method");
    }

    // 充当destroy method
    public void destroy() {
        LOGGER.info("FileReader destroy-method");
    }
}
