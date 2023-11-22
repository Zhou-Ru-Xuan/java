package com.zhouruxuan.util.excel.entity.opencsv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class ViewWithPosition {
    @CsvBindByPosition(position = 0)
    private Long partnerId;

    @CsvBindByPosition(position = 1)
    private Long poiId;

    @CsvBindByPosition(position = 2)
    private Long giftId;

    // 房型码+RP码 与 goodsID两者只存在一种，这里为了复用类，就不设置position了
    @CsvBindByPosition(position = 3)
    private String roomTypeCode;

    @CsvBindByPosition(position = 4)
    private String ratePlanCode;

    @CsvBindByPosition(position = 5)
    private Long goodsId;

    @CsvBindByPosition(position = 6)
    private String reason;
}
