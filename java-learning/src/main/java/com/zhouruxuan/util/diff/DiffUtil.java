package com.zhouruxuan.util.diff;

import com.alibaba.fastjson.JSON;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class DiffUtil {
    public static boolean isMatching(Object oldData, Object newData) throws Exception {
        if (oldData == null && newData == null) {
            return true;
        }
        if (oldData == null || newData == null) {
            return false;
        }
        return JSONCompare.compareJSON(JSON.toJSONString(oldData), JSON.toJSONString(newData), JSONCompareMode.NON_EXTENSIBLE).passed();
    }
}
