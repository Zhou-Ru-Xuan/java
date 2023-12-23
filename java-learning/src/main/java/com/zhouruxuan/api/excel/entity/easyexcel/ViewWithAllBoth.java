package com.zhouruxuan.api.excel.entity.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewWithAllBoth {
    @ExcelProperty(value = "A", index = 0)
    private Long a;
    @ExcelProperty(value = "B", index = 1)
    private Long b;
    @ExcelProperty(value = "C", index = 2)
    private Long c;
    @ExcelProperty(value = "D", index = 3)
    private String d;
    @ExcelProperty(value = "E", index = 4)
    private String e;
}
