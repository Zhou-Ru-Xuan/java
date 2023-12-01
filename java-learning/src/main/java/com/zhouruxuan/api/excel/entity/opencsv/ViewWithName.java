package com.zhouruxuan.api.excel.entity.opencsv;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ViewWithName {
    @CsvBindByName(column = "A")
    private Long a;

    @CsvBindByName(column = "B")
    private Long b;

    @CsvBindByName(column = "C")
    private Long c;

    @CsvBindByName(column = "D")
    private String d;

    @CsvBindByName(column = "E")
    private String e;
}
