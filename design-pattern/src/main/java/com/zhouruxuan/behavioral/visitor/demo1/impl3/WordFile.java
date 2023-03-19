package com.zhouruxuan.behavioral.visitor.demo1.impl3;


/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-14
 **/
public class WordFile extends ResourceFile {

    public WordFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Extractor extractor) {
        extractor.extract2txt(this);
    }
}
