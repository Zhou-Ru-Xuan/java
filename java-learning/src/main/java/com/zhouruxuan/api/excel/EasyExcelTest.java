package com.zhouruxuan.api.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.zhouruxuan.api.excel.entity.easyexcel.ViewWithBoth;
import com.zhouruxuan.api.excel.entity.easyexcel.ViewWithName;
import com.zhouruxuan.api.excel.entity.easyexcel.ViewWithPosition;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

public class EasyExcelTest {
    InputStream inputStream = getClass().getResourceAsStream("/excel/ACE.csv");
    InputStream excelInputStream = getClass().getResourceAsStream("/excel/ACE.xlsx");

    public EasyExcelTest() {
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
    public void testWithNameReadExcel() {
        try {
            List<ViewWithName> views = EasyExcelFactory.read(excelInputStream)
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

    @Test
    public void write() {
        try {
            List<ViewWithName> views = EasyExcelFactory.read(inputStream)
                    .head(ViewWithName.class)
                    .sheet()
                    .doReadSync();


            // 创建ExcelWriter对象
            ExcelWriter excelWriter = EasyExcel.write("easyExcel-write.csv").build();

            // 创建Sheet对象，指定写入的sheet名称
            WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").head(ViewWithName.class).build();

            // 写入数据
            excelWriter.write(views, writeSheet);

            // 关闭ExcelWriter对象，释放资源
            excelWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
