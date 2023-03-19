package com.zhouruxuan.behavioral.visitor.demo1.impl5;

public interface Visitor {
  void visit(PdfFile pdfFile);
  void visit(PPTFile pdfFile);
  void visit(WordFile pdfFile);
}