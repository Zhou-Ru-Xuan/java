package com.zhouruxuan.api.excel.entity.opencsv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class ViewWithBoth {
    @CsvBindByName(column = "partnerID")
    @CsvBindByPosition(position = 0)
    private Long partnerId;

    @CsvBindByName(column = "poiID")
    @CsvBindByPosition(position = 1)
    private Long poiId;

    @CsvBindByName(column = "礼包ID")
    private Long giftId;

    // 房型码+RP码 与 goodsID两者只存在一种，这里为了复用类，就不设置position了
    @CsvBindByName(column = "房型码")
    private String roomTypeCode;

    @CsvBindByName(column = "RP码")
    private String ratePlanCode;

    @CsvBindByName(column = "goodsID")
    @CsvBindByPosition(position = 5)
    private Long goodsId;

    @CsvBindByName(column = "原因")
    @CsvBindByPosition(position = 6)
    private String reason;
}
