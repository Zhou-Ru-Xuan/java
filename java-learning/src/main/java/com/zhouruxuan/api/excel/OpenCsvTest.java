package com.zhouruxuan.api.excel;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.zhouruxuan.api.excel.entity.opencsv.ViewWithBoth;
import com.zhouruxuan.api.excel.entity.opencsv.ViewWithName;
import com.zhouruxuan.api.excel.entity.opencsv.ViewWithPosition;
import org.apache.commons.io.input.BOMInputStream;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    @Test
    public void createCsvFileByColumnName() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        ViewWithName viewWithName = new ViewWithName();
        viewWithName.setPartnerId(1234L);
        viewWithName.setPoiId(2234L);
        List<ViewWithName> viewWithNames = new ArrayList<>();
        viewWithNames.add(viewWithName);
        createCsvFileByColumnName("1234.csv", viewWithNames);
    }

    public void createCsvFileByColumnName(String fileName, List<ViewWithName> dataList) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        File file = new File(fileName);

        HeaderColumnNameMappingStrategy<ViewWithName> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
        mappingStrategy.setType(ViewWithName.class);
        Writer writer = null;

        writer = new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8);
        //设置bom
        writer.write(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));
        StatefulBeanToCsv<ViewWithName> beanToCsv = new StatefulBeanToCsvBuilder<ViewWithName>(writer)
                .withMappingStrategy(mappingStrategy).withSeparator(',').withApplyQuotesToAll(false).build();
        beanToCsv.write(dataList);

        writer.close();
    }

}
