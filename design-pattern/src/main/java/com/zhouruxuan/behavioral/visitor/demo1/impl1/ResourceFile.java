package com.zhouruxuan.behavioral.visitor.demo1.impl1;

public abstract class ResourceFile {
  protected String filePath;

  public ResourceFile(String filePath) {
    this.filePath = filePath;
  }

  public abstract void extract2txt();
}