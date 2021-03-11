package com.winner.commons.word.poitl.data;

import com.deepoove.poi.data.*;

/**
 * .
 *
 * @Classname ColspanCells
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/2 上午9:16
 */
public class ColspanCells {

    private ColspanCells() {
    }

    public static ColspanCells.ColspanCellsBuilder of() {
        ColspanCells.ColspanCellsBuilder inst = new ColspanCells.ColspanCellsBuilder();
        return inst;
    }

    public static class ColspanCellsBuilder implements RenderDataBuilder<ColspanCellRenderData> {

        private ColspanCellRenderData data;

        private ColspanCellsBuilder() {
            data = new ColspanCellRenderData();
        }

        public ColspanCellsBuilder put(String key, Object value) {
            data.put(key, value);
            return this;
        }

        @Override
        public ColspanCellRenderData create() {
            return data;
        }
    }
}
