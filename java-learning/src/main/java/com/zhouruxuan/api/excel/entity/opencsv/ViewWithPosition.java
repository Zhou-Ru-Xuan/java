package com.zhouruxuan.api.excel.entity.opencsv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class ViewWithPosition {
    @CsvBindByPosition(position = 0)
    private Long a;

    @CsvBindByPosition(position = 1)
    private Long b;

    @CsvBindByPosition(position = 2)
    private Long c;

    @CsvBindByPosition(position = 3)
    private String d;

    @CsvBindByPosition(position = 4)
    private String e;
}
