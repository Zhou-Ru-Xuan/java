package com.zhouruxuan.api.excel.entity.opencsv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewWithBoth {
    @CsvBindByName(column = "A")
    @CsvBindByPosition(position = 0)
    private Long a;

    @CsvBindByName(column = "B")
    @CsvBindByPosition(position = 1)
    private Long b;

    @CsvBindByName(column = "C")
    @CsvBindByPosition(position = 2)
    private Long c;

    @CsvBindByName(column = "D")
    @CsvBindByPosition(position = 3)
    private String d;

    @CsvBindByName(column = "E")
    @CsvBindByPosition(position = 4)
    private String e;
}
