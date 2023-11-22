package com.zhouruxuan.util.excel;

import com.opencsv.bean.CsvToBeanBuilder;
import com.zhouruxuan.util.excel.entity.opencsv.ViewWithBoth;
import com.zhouruxuan.util.excel.entity.opencsv.ViewWithName;
import com.zhouruxuan.util.excel.entity.opencsv.ViewWithPosition;
import org.apache.commons.io.input.BOMInputStream;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
public class OpenCsvTest {
    String filePath = "/Users/zhouruxuan/Documents/code/java/java/java-learning/src/main/java/com/zhouruxuan/util/excel/resource/上传绑定礼包与房型码RP码测试.csv";

    @Test
    public void testWithName() {
        try {
            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
            InputStreamReader reader = new InputStreamReader(BOMInputStream.builder().setInputStream(inputStream).get(), StandardCharsets.UTF_8);

            List<ViewWithName> views = new CsvToBeanBuilder<ViewWithName>(reader)
                    .withType(ViewWithName.class)
                    .build()
                    .parse();

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
            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
            InputStreamReader reader = new InputStreamReader(BOMInputStream.builder().setInputStream(inputStream).get(), StandardCharsets.UTF_8);

            List<ViewWithPosition> views = new CsvToBeanBuilder<ViewWithPosition>(reader)
                    .withSkipLines(1)
                    .withType(ViewWithPosition.class)
                    .build()
                    .parse();

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
            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
            InputStreamReader reader = new InputStreamReader(BOMInputStream.builder().setInputStream(inputStream).get(), StandardCharsets.UTF_8);

            List<ViewWithBoth> views = new CsvToBeanBuilder<ViewWithBoth>(reader)
                    .withSkipLines(1)
                    .withType(ViewWithBoth.class)
                    .build()
                    .parse();

            for (ViewWithBoth view : views) {
                System.out.println(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
