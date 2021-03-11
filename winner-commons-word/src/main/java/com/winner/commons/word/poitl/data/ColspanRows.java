package com.winner.commons.word.poitl.data;

import com.deepoove.poi.data.RenderDataBuilder;

import java.util.Arrays;

/**
 * .
 *
 * @Classname Colspans
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/1 下午2:49
 */
public class ColspanRows {

    private ColspanRows() {
    }

    public static ColspanRows.ColspansBuilder of() {
        ColspanRows.ColspansBuilder inst = new ColspanRows.ColspansBuilder();
        return inst;
    }

    public static ColspanRows.ColspansBuilder of(ColspanCellRenderData... cells) {
        ColspanRows.ColspansBuilder inst = of();
        if (null != cells) {
            Arrays.stream(cells).forEach(inst::addCell);
        }
        return inst;
    }

    public static ColspanRowRenderData create() {
        return of().create();
    }

    public static ColspanRowRenderData create(ColspanCellRenderData... cells) {
        return of(cells).create();
    }

    public static class ColspansBuilder implements RenderDataBuilder<ColspanRowRenderData>  {

        private ColspanRowRenderData data;

        private ColspansBuilder() {
            data = new ColspanRowRenderData();
        }

        public void addCell(ColspanCellRenderData cell) {
            data.addCell(cell);
        }

        @Override
        public ColspanRowRenderData create() {
            return data;
        }
    }
}
