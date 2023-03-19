package com.zhouruxuan.behavioral.visitor.demo1.impl4;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-14
 **/
public class PPTFile extends ResourceFile{

    public PPTFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Extractor extractor) {

    }

    @Override
    public void accept(Compressor compressor) {
        compressor.compress(this);
    }

}
