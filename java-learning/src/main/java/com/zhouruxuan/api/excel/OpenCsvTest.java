package com.zhouruxuan.api.excel;

import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.zhouruxuan.api.excel.entity.opencsv.ViewWithBoth;
import com.zhouruxuan.api.excel.entity.opencsv.ViewWithName;
import com.zhouruxuan.api.excel.entity.opencsv.ViewWithPosition;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class OpenCsvTest {
    InputStream inputStream = getClass().getResourceAsStream("/excel/ACE.csv");

    InputStream excelInputStream = getClass().getResourceAsStream("/excel/ACE.xlsx");


    @Test
    public void testWithNameWithSkipLines() {
        try {
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            List<ViewWithName> views = new CsvToBeanBuilder<ViewWithName>(reader)
                    .withSkipLines(1)
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
     * 通过名称映射（设置bom）
     */
    @Test
    public void testWithNameReadExcel() {
        try {
            InputStreamReader reader = new InputStreamReader(BOMInputStream.builder().setInputStream(excelInputStream).get(), StandardCharsets.UTF_8);

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

    @Test
    public void createCsvFileByColumnPosition() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        List<ViewWithBoth> viewWithBoths = new ArrayList<>();
        viewWithBoths.add(new ViewWithBoth(1L, 2L, 3L, "4", "5"));
        viewWithBoths.add(new ViewWithBoth(6L, 7L, 8L, "9", "10"));
        viewWithBoths.add(new ViewWithBoth(6L, null, 8L, null, null));


        FileWriter writer = new FileWriter("src/main/resources/csv/createCsvFileByColumnPosition.csv");
        writer.write(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));

        CustomMappingStrategy<ViewWithBoth> strategy = new CustomMappingStrategy<>();
        strategy.setType(ViewWithBoth.class);

        StatefulBeanToCsv<ViewWithBoth> beanToCsv = new StatefulBeanToCsvBuilder<ViewWithBoth>(writer)
                .withMappingStrategy(strategy)
                .withSeparator(',')
                .withApplyQuotesToAll(false)
                .build();

        beanToCsv.write(viewWithBoths);

        writer.close();
    }

    class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {
        @Override
        public String[] generateHeader(T t) throws CsvRequiredFieldEmptyException {
            //  调用父类的generateHeader方法，初始化必要的信息
            super.generateHeader(t);

            final int numColumns = findMaxFieldIndex();

            String[] header = new String[numColumns + 1];

            BeanField beanField;
            for (int i = 0; i <= numColumns; i++) {
                beanField = findField(i);
                String columnHeaderName = extractHeaderName(beanField);
                header[i] = columnHeaderName;
            }
            return header;
        }

        private String extractHeaderName(final BeanField beanField) {
            if (beanField == null || beanField.getField() == null || beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class).length == 0) {
                return StringUtils.EMPTY;
            }

            final CsvBindByName bindByNameAnnotation = beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class)[0];
            return bindByNameAnnotation.column();
        }
    }
}

