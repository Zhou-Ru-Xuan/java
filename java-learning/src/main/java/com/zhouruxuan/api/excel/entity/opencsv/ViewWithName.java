package com.zhouruxuan.api.excel.entity.opencsv;

import com.opencsv.bean.CsvBindByName;
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
    @CsvBindByName(column = "partnerID")
    private Long partnerId;

    @CsvBindByName(column = "poiID")
    private Long poiId;

    @CsvBindByName(column = "礼包ID")
    private Long giftId;

    // 房型码+RP码 与 goodsID两者只存在一种，这里为了复用类，就不设置position了
    @CsvBindByName(column = "房型码")
    private String roomTypeCode;

    @CsvBindByName(column = "RP码")
    private String ratePlanCode;

    @CsvBindByName(column = "goodsID")
    private Long goodsId;

    @CsvBindByName(column = "原因")
    private String reason;
}
