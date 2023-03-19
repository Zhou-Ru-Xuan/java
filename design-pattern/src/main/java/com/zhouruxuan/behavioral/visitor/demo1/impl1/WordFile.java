package com.zhouruxuan.behavioral.visitor.demo1.impl1;

import com.zhouruxuan.behavioral.visitor.demo1.impl1.ResourceFile;

public class WordFile extends ResourceFile {
  public WordFile(String filePath) {
    super(filePath);
  }

  @Override
  public void extract2txt() {
    //...
    System.out.println("Extract WORD.");
  }
}