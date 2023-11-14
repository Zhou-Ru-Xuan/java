package com.example.springlearning.entity.file;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileProcessor {
    public String process() {
        return "FileProcessor processed";
    }

    public FileProcessor() {
        LOGGER.info("FileProcessor constructor");
    }

    // 充当init method
    public void init() {
        LOGGER.info("FileProcessor init-method");
    }

    // 充当destroy method
    public void destroy() {
        LOGGER.info("FileProcessor destroy-method");
    }
}
