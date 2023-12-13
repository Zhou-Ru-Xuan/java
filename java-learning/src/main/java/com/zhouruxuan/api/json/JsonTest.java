package com.zhouruxuan.api.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zhouruxuan.api.compress.CompressUtil;
import entity.*;
import lombok.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

public class JsonTest {
    @Test
    public void testNullJson() {
        Person person = new Person();
        String jsonString = JSON.toJSONString(person);
        System.out.println(jsonString);
    }

    @Test
    public void testMapJson() {
        String jsonString = "[\n" +
                "  {\"13356\":0},\n" +
                "  {\"321465\":1},\n" +
                "  {\"1234321\":3}\n" +
                "]";
        // 解析JSON字符串为Map<String, Integer>
        Map<String, Integer> map = new HashMap<>();
        for (Object obj : JSON.parseArray(jsonString)) {
            JSONObject jsonObject = (JSONObject) obj;
            for (String key : jsonObject.keySet()) {
                Integer value = jsonObject.getInteger(key);
                map.put(key, value);
            }
        }

        // 遍历Map，处理每个键值对
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            // 处理每个键值对，例如打印key和value
            System.out.println("Key: " + key);
            System.out.println("Value: " + value);
            // 其他处理
        }
    }

    @Test
    public void getIdFromJsonString() throws IOException {
        String jsonString = FastJsonUtil.readJsonFile("/Users/zhouruxuan/Documents/code/java/java/java-learning/src/main/java/com/zhouruxuan/api/json/mtaGoodsList.json");
        List<RoomGood> idFromJsonString = getIdFromJsonString(jsonString);
        idFromJsonString.addAll(new ArrayList<>(idFromJsonString));
        idFromJsonString.addAll(new ArrayList<>(idFromJsonString));

        RoomGoodsResult roomGoodsResult = new RoomGoodsResult(idFromJsonString);
        String jsonString1 = JSON.toJSONString(roomGoodsResult);
        System.out.println(jsonString1);
        String compress = CompressUtil.GzipCompress(jsonString1);
        System.out.println(compress);
        System.out.println(CompressUtil.GzipDecompress(compress).equals(jsonString1));

        System.out.println(JSONObject.parseObject(jsonString1, RoomGoodsResult.class).equals(roomGoodsResult));
    }

    private List<RoomGood> getIdFromJsonString(String json) {
        List<RoomGood> roomGoods = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray realRoomRelationResults = jsonObject.getJSONObject("data").getJSONArray("realRoomRelationResults");
        for (Object realRoomRelationResult : realRoomRelationResults) {
            JSONObject jsonObject1 = (JSONObject) realRoomRelationResult;
            // RoomGood roomGood = new RoomGood(jsonObject1.getLong("realRoomId"), jsonObject1.getString("realRoomName"), new ArrayList<>());
            RoomGood roomGood = new RoomGood(jsonObject1.getLong("realRoomId"), null, new ArrayList<>());

            JSONArray mtaDealingPrepayGoodsResults = jsonObject1.getJSONArray("mtaDealingPrepayGoodsResults");
            for (Object mtaDealingPrepayGoodsResult : mtaDealingPrepayGoodsResults) {
                JSONObject mtaDealingPrepayGoodsResult1 = (JSONObject) mtaDealingPrepayGoodsResult;
                // LogicRoom logicRoom = new LogicRoom(new RoomType(mtaDealingPrepayGoodsResult1.getJSONObject("roomType").getLong("roomId"),
                //         mtaDealingPrepayGoodsResult1.getJSONObject("roomType").getString("roomName")),
                //         new ArrayList<>());
                LogicRoom logicRoom = new LogicRoom(new RoomType(mtaDealingPrepayGoodsResult1.getJSONObject("roomType").getLong("roomId"),
                        null),
                        new ArrayList<>());
                JSONArray goodsList1 = mtaDealingPrepayGoodsResult1.getJSONArray("goodsList");
                List<Goods> goodsList = new ArrayList<>();

                for (Object o : goodsList1) {
                    JSONObject o1 = (JSONObject) o;
                    // goodsList.add(new Good(o1.getLong("goodsId"), o1.getString("goodsName")));
                    goodsList.add(new Goods(o1.getLong("goodsId"), null));
                }

                logicRoom.getGoodsList().addAll(goodsList);
                roomGood.getLogicRoomRelations().add(logicRoom);
            }
            roomGoods.add(roomGood);
        }
        return roomGoods;
    }


    public List<RoomGood> convertToRoomGood(Map<Long, Map<Long, List<Long>>> dataMap) {
        List<RoomGood> roomGoods = new ArrayList<>();

        for (Map.Entry<Long, Map<Long, List<Long>>> entry : dataMap.entrySet()) {
            Long realRoomId = entry.getKey();
            Map<Long, List<Long>> roomMap = entry.getValue();

            RoomGood roomGood = new RoomGood();
            roomGood.setRealRoomId(realRoomId);

            List<LogicRoom> logicRooms = new ArrayList<>();

            for (Map.Entry<Long, List<Long>> roomEntry : roomMap.entrySet()) {
                Long roomId = roomEntry.getKey();
                List<Long> goodsList = roomEntry.getValue();

                LogicRoom logicRoom = new LogicRoom();
                RoomType roomType = new RoomType(roomId);
                logicRoom.setRoomType(roomType);

                List<Goods> goods = new ArrayList<>();

                for (Long goodsId : goodsList) {
                    Goods good = new Goods();
                    good.setGoodsId(goodsId);
                    goods.add(good);
                }

                logicRoom.setGoodsList(goods);
                logicRooms.add(logicRoom);
            }

            roomGood.setLogicRoomRelations(logicRooms);
            roomGoods.add(roomGood);
        }

        return roomGoods;
    }

    @Test
    public void testEnum() {
        List<RoomGoodsSort> enums = Arrays.asList(RoomGoodsSort.ASC, RoomGoodsSort.DESC);
        System.out.println(JSON.toJSONString(enums));
    }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {
    private int id;
    private String name;
}

@Getter
enum RoomGoodsSort {
    ASC(1, "Ascending"),
    DESC(2, "Descending");

    RoomGoodsSort(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private final Integer type;
    private final String desc;

}