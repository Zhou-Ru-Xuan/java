package org.example;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class Main {
    public static void main(String[] args) {
//        String json = "123";
//        String json2 = "Invalid_Json";
//        String json3 = "{}";
//        String json4 = "{\"a\":123}";
//
////        assertTrue(isValid2(json));
////        assertTrue(isValid2(json2));
//
//        assertTrue(isValid2(json3));
//
//        assertTrue(isValid2(json4));
        String obj = "[{\"commander\":\"zhouruxuan\",\"configData\":\"{\"0\":\"123\",\"123\":\"321\"}\",\"configName\":\"PR\",\"configType\":0,\"dataType\":7,\"gmtCreate\":\"2023-07-06T11:51:17\",\"gmtModified\":\"2023-07-12T11:48:02\",\"remark\":\"价格审核中 加审审批 分战区指定审核人 0为兜底审核人\",\"status\":1,\"usedAppkey\":\"com.sankuai.hotel.biz.platform\",\"validBegin\":\"2023-07-06T11:50:18\",\"validEnd\":\"2033-07-06T11:50:43\"}]";
        assertTrue(isValid2("[{},{}]"));
        JSON.parseArray("[{},{}]");

    }

    static final TypeAdapter<JsonElement> strictAdapter = new Gson().getAdapter(JsonElement.class);

    public static boolean isValid2(String json) {
        try {
            JsonElement jsonElement = strictAdapter.fromJson(json);
            return jsonElement.isJsonObject() || jsonElement.isJsonArray();
        } catch (JsonSyntaxException | IOException e) {
            return false;
        }
    }
}