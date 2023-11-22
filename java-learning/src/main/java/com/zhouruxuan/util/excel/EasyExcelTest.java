package com.zhouruxuan.util.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.zhouruxuan.util.excel.entity.easyexcel.ViewWithBoth;
import com.zhouruxuan.util.excel.entity.easyexcel.ViewWithName;
import com.zhouruxuan.util.excel.entity.easyexcel.ViewWithPosition;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class EasyExcelTest {
    String filePath = "/Users/zhouruxuan/Documents/code/java/java/java-learning/src/main/java/com/zhouruxuan/util/excel/resource/上传绑定礼包与房型码RP码测试.csv";
    InputStream inputStream = new FileInputStream(filePath);

    public EasyExcelTest() throws FileNotFoundException {
    }

    @Test
    public void testWithName() {
        try {
            List<ViewWithName> views = EasyExcelFactory.read(inputStream)
                    .head(ViewWithName.class)
                    .sheet()
                    .doReadSync();

            for (ViewWithName view : views) {
                System.out.println(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWithPosition() {
        try {
            List<ViewWithPosition> views = EasyExcelFactory.read(inputStream)
                    .head(ViewWithPosition.class)
                    .sheet()
                    .doReadSync();

            for (ViewWithPosition view : views) {
                System.out.println(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWithBoth() {
        try {
            List<ViewWithBoth> views = EasyExcelFactory.read(inputStream)
                    .head(ViewWithBoth.class)
                    .sheet()
                    .doReadSync();

            for (ViewWithBoth view : views) {
                System.out.println(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
