package com.zhouruxuan.mybatis.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class RoomGoodsSortSettingsDO {
    private Long id;

    private Long partnerId;

    private Long poiId;

    private Integer userType;

    private Integer userId;

    private String userLogin;

    private String roomGoodsWeight;

    private String goodsAutoSort;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    /**
     * 将JSON结构的产品房型权限解析成map
     *
     * @return key:房型ID/产品ID   value:权重
     */
    public Map<Long, Integer> parseRoomGoodsWeight() {
        if (StringUtils.isEmpty(roomGoodsWeight)) {
            return new HashMap<>();
        }

        Map<Long, Integer> map = new HashMap<>();
        try {
            for (Object obj : JSON.parseArray(roomGoodsWeight)) {
                JSONObject jsonObject = (JSONObject) obj;
                for (String key : jsonObject.keySet()) {
                    Integer value = jsonObject.getInteger(key);
                    map.put(Long.parseLong(key), value);
                }
            }
        } catch (Exception e) {
            LOGGER.error("eid={} roomGoodsWeight={}", "RoomGoodsSortSettingsDO.parseRoomGoodsWeight", roomGoodsWeight, e);
        }
        return map;
    }

    /**
     * 将JSON结构的产品自动排序解析成JavaBean
     *
     * @return
     */
    public List<GoodsSortRuleItemModel> parseGoodsAutoSort() {
        if (StringUtils.isEmpty(goodsAutoSort)) {
            return new ArrayList<>();
        }
        try {
            return JSON.parseArray(goodsAutoSort, GoodsSortRuleItemModel.class);
        } catch (Exception e) {
            LOGGER.error("eid={} goodsAutoSort={}", "RoomGoodsSortSettingsDO.parseGoodsAutoSort", goodsAutoSort, e);
        }
        return new ArrayList<>();
    }

}
