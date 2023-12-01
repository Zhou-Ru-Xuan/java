package com.zhouruxuan.api.excel.entity.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ViewWithName {
    @ExcelProperty("A")
    private Long a;

    @ExcelProperty("B")
    private Long b;

    @ExcelProperty("C")
    private Long c;

    @ExcelProperty("D")
    private String d;

    @ExcelProperty("E")
    private String e;
}
