package com.zhouruxuan.api.excel.entity.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 跨vpoi关联礼包和商品
 */
@Getter
@Setter
@ToString
public class ViewWithName {
    @ExcelProperty("partnerID")
    private Long partnerId;

    @ExcelProperty("poiID")
    private Long poiId;

    @ExcelProperty("礼包ID")
    private Long giftId;

    // 房型码+RP码 与 goodsID两者只存在一种，这里为了复用类，就不设置position了
    @ExcelProperty("房型码")
    private String roomTypeCode;

    @ExcelProperty("RP码")
    private String ratePlanCode;

    @ExcelProperty("goodsID")
    private Long goodsId;

    @ExcelProperty("原因")
    private String reason;
}
