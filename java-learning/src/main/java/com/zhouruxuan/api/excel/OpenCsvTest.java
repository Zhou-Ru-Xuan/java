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
import java.util.ArrayList;
import java.util.List;

public class OpenCsvTest {
    InputStream inputStream = getClass().getResourceAsStream("/excel/ACE.csv");

    /**
     * 通过英文名称映射（不设置bom）
     */
    @Test
    public void testWithNameNoBom() {
        try {
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

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


    /**
     * 通过名称映射（设置bom）
     */
    @Test
    public void testWithName() {
        try {
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

    /**
     * 通过列的下标映射，不跳过第一行
     */
    @Test
    public void testWithPositionNoSkipLine() {
        try {
            InputStreamReader reader = new InputStreamReader(BOMInputStream.builder().setInputStream(inputStream).get(), StandardCharsets.UTF_8);

            List<ViewWithPosition> views = new CsvToBeanBuilder<ViewWithPosition>(reader)
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

    /**
     * 通过列的下标映射（跳过第一行）
     */
    @Test
    public void testWithPosition() {
        try {
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

    /**
     * 同时使用两个注解（不跳过第一行）
     */
    @Test
    public void testWithBothNoSkipLine() {
        try {
            InputStreamReader reader = new InputStreamReader(BOMInputStream.builder().setInputStream(inputStream).get(), StandardCharsets.UTF_8);

            List<ViewWithBoth> views = new CsvToBeanBuilder<ViewWithBoth>(reader)
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
    public void testWithBoth() {
        try {
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
        viewWithName.setA(1234L);
        viewWithName.setB(2234L);
        List<ViewWithName> viewWithNames = new ArrayList<>();
        viewWithNames.add(viewWithName);
        createCsvFileByColumnName("write-1234.csv", viewWithNames);
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
