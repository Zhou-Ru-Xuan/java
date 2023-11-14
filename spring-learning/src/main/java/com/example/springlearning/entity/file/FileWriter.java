package com.example.springlearning.entity.file;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileWriter {
    public FileWriter() {
        LOGGER.info("FileWriter constructor");
    }

    // 充当init method
    public void init() {
        LOGGER.info("FileWriter init-method");
    }

    // 充当destroy method
    public void destroy() {
        LOGGER.info("FileWriter destroy-method");
    }
}
