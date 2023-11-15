package com.example.springlearning;

import com.example.springlearning.entity.file.FileProcessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class DependOnTest {

    @Resource
    private FileProcessor fileProcessor;

    @Test
    public void WhenFileProcessorIsCreated_FileTextContains_Processed() {
        assertTrue(fileProcessor.process().endsWith("processed"));
    }
}
