package com.zhouruxuan.behavioral.visitor.demo1.impl1;

public class PdfFile extends ResourceFile {
  public PdfFile(String filePath) {
    super(filePath);
  }

  @Override
  public void extract2txt() {
    //...
    System.out.println("Extract PDF.");
  }
}