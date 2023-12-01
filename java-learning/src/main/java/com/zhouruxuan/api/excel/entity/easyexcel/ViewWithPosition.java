package com.zhouruxuan.api.excel.entity.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ViewWithPosition {
    @ExcelProperty(index = 0)
    private Long a;
    @ExcelProperty(index = 1)
    private Long b;
    @ExcelProperty(index = 2)
    private Long c;
    @ExcelProperty(index = 3)
    private String d;
    @ExcelProperty(index = 4)
    private String e;
}
