package com.zhouruxuan.api.excel.entity.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ViewWithPosition {
    @ExcelProperty(index = 0)
    private Long partnerId;

    @ExcelProperty(index = 1)
    private Long poiId;
    @ExcelProperty(index = 2)
    private Long giftId;

    // 房型码+RP码 与 goodsID两者只存在一种，这里为了复用类，就不设置position了
    @ExcelProperty(index = 3)
    private String roomTypeCode;
    @ExcelProperty(index = 4)
    private String ratePlanCode;
    @ExcelProperty(index = 5)
    private Long goodsId;
    @ExcelProperty(index = 6)
    private String reason;
}
