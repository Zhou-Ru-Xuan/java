package com.zhouruxuan.api.excel.entity.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ViewWithName {
    @ExcelProperty("E")
    private Long a;

    @ExcelProperty("D")
    private Long b;

    @ExcelProperty("C")
    private Long c;

    @ExcelProperty("B")
    private String d;

    @ExcelProperty("A")
    private String e;
}
