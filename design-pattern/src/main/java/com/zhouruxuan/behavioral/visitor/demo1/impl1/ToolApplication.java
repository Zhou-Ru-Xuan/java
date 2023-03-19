package com.zhouruxuan.behavioral.visitor.demo1.impl1;

import com.zhouruxuan.behavioral.visitor.demo1.impl1.PPTFile;
import com.zhouruxuan.behavioral.visitor.demo1.impl1.PdfFile;
import com.zhouruxuan.behavioral.visitor.demo1.impl1.ResourceFile;
import com.zhouruxuan.behavioral.visitor.demo1.impl1.WordFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 假设我们从网站上爬取了很多资源文件，它们的格式有三种：PDF、PPT、Word。我们现在要开发一个工具来处理这批资源文件。这个工具的其中一个功能是，把这些资源文件中的文本内容抽取出来放到txt文件中
 */
public class ToolApplication {
    public static void main(String[] args) {
        List<ResourceFile> resourceFiles = listAllResourceFiles(args[0]);
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.extract2txt();
        }
    }

    private static List<ResourceFile> listAllResourceFiles(String resourceDirectory) {
        List<ResourceFile> resourceFiles = new ArrayList<>();
        //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PdfFile/PPTFile/WordFile)
        resourceFiles.add(new PdfFile("a.pdf"));
        resourceFiles.add(new WordFile("b.word"));
        resourceFiles.add(new PPTFile("c.ppt"));
        return resourceFiles;
    }
}