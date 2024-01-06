package com.zhouruxuan.api.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.zhouruxuan.api.excel.entity.ErrorCodeEnum;
import com.zhouruxuan.api.excel.entity.easyexcel.ViewWithAllBoth;
import com.zhouruxuan.api.excel.entity.easyexcel.ViewWithBoth;
import com.zhouruxuan.api.excel.entity.easyexcel.ViewWithName;
import com.zhouruxuan.api.excel.entity.easyexcel.ViewWithPosition;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
                    .excelType(ExcelTypeEnum.CSV)
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
    public void testWithAllBoth() {
        try {
            List<ViewWithAllBoth> views = EasyExcelFactory.read(inputStream)
                    .head(ViewWithAllBoth.class)
                    .sheet()
                    .doReadSync();

            for (ViewWithAllBoth view : views) {
                System.out.println(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void write() {
        try {
            List<ViewWithAllBoth> views = new ArrayList<>();
            views.add(new ViewWithAllBoth(1L, null, 3L, "4", null));
            views.add(new ViewWithAllBoth(6L, null, 8L, "9", null));
            views.add(new ViewWithAllBoth(11L, null, 13L, "18", null));

            // 创建ExcelWriter对象
            ExcelWriter excelWriter = EasyExcel.write("src/main/resources/csv/easyExcel-write.csv").build();

            // 创建Sheet对象，指定写入的sheet名称
            WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").head(ViewWithAllBoth.class).build();

            // 写入数据
            excelWriter.write(views, writeSheet);

            // 关闭ExcelWriter对象，释放资源
            excelWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeByName() {
        try {
            List<ViewWithName> views = new ArrayList<>();
            views.add(new ViewWithName(1L, null, 3L, "4", null));
            views.add(new ViewWithName(6L, null, 8L, "9", null));
            views.add(new ViewWithName(11L, null, 13L, "18", null));

            // 创建ExcelWriter对象
            ExcelWriter excelWriter = EasyExcel.write("src/main/resources/csv/easyExcel-writeByName.csv").build();

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

    @Test
    public void writeWithExceptedCol() {
        try {
            List<ViewWithAllBoth> views = new ArrayList<>();
            views.add(new ViewWithAllBoth(1L, null, 3L, "4", null));
            views.add(new ViewWithAllBoth(6L, null, 8L, "9", null));
            views.add(new ViewWithAllBoth(11L, null, 13L, "18", null));

            // 创建ExcelWriter对象
            ExcelWriter excelWriter = EasyExcel.write("src/main/resources/csv/easyExcel-write-ExceptedCol.csv")
                    .head(head())
                    .includeColumnFieldNames(Arrays.asList("a", "c", "d"))
                    .build();

            // 创建Sheet对象，指定写入的sheet名称
            WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").build();

            // 写入数据
            excelWriter.write(views, writeSheet);

            // 关闭ExcelWriter对象，释放资源
            excelWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<List<String>> head() {
        List<List<String>> head = new ArrayList<>();
        List<String> row1 = new ArrayList<>();
        row1.add("A");
        List<String> row2 = new ArrayList<>();
        row2.add("C");
        List<String> row3 = new ArrayList<>();
        row3.add("D");
        head.add(row1);
        head.add(row2);
        head.add(row3);
        return head;
    }

    @Test
    public void writeErrorCodeEnum() {
        // 写入Excel文件
        String fileName = "/Users/zhouruxuan/Documents/code/java/java/java-learning/src/main/resources/excel/ErrorCodeEnum.xlsx";
        EasyExcel.write(fileName)
                .sheet("Sheet1")
                .doWrite(Arrays.asList(ErrorCodeEnum.values()));
    }
}
