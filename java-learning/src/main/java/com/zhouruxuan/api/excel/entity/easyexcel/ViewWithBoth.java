package com.zhouruxuan.api.excel.entity.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ViewWithBoth {
    @ExcelProperty(value = "partnerID", index = 0)
    private Long partnerId;

    @ExcelProperty(value = "poiID", index = 1)
    private Long poiId;
    @ExcelProperty(value = "礼包ID")
    private Long giftId;

    // 房型码+RP码 与 goodsID两者只存在一种，这里为了复用类，就不设置position了
    @ExcelProperty(value = "房型码")
    private String roomTypeCode;
    @ExcelProperty(value = "RP码", index = 4)
    private String ratePlanCode;
    @ExcelProperty(value = "goodsID", index = 5)
    private Long goodsId;
    @ExcelProperty(value = "原因", index = 6)
    private String reason;
}
