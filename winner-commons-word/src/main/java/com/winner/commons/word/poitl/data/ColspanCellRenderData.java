package com.winner.commons.word.poitl.data;

import com.deepoove.poi.data.RenderData;

import java.util.HashMap;
import java.util.Map;

/**
 * .
 *
 * @Classname ColspanCellRenderData
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/2 上午9:08
 */
public class ColspanCellRenderData implements RenderData {

    private Map<String, Object> data = new HashMap<>();

    public void put(String key, Object value) {
        data.put(key, value);
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public Object getValue(String key) {
        return this.data.get(key);
    }
}
