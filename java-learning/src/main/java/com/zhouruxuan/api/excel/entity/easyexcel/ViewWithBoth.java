package com.zhouruxuan.api.excel.entity.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ViewWithBoth {
    @ExcelProperty(value = "A", index = 0)
    private Long a;
    @ExcelProperty(value = "B", index = 1)
    private Long b;
    @ExcelProperty(value = "C", index = 2)
    private Long c;
    @ExcelProperty(value = "D")
    private String d;
    @ExcelProperty(value = "E")
    private String e;
}
