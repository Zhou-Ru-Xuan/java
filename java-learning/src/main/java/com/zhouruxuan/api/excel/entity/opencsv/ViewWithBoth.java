package com.zhouruxuan.api.excel.entity.opencsv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class ViewWithBoth {
    @CsvBindByName(column = "A")
    @CsvBindByPosition(position = 0)
    private Long a;

    @CsvBindByName(column = "B")
    @CsvBindByPosition(position = 1)
    private Long b;

    @CsvBindByName(column = "C")
    private Long c;

    @CsvBindByName(column = "D")
    private String d;

    @CsvBindByName(column = "E")
    @CsvBindByPosition(position = 4)
    private String e;
}
