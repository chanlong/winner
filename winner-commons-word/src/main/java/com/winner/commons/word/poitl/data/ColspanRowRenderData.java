package com.winner.commons.word.poitl.data;

import com.deepoove.poi.data.RenderData;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @Classname ColspanRowRenderData
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/2 上午9:05
 */
public class ColspanRowRenderData implements RenderData {

    private List<ColspanCellRenderData> cells = new ArrayList<>();

    public ColspanRowRenderData addCell(ColspanCellRenderData cell) {
        cells.add(cell);
        return this;
    }

    public List<ColspanCellRenderData> getCells() {
        return this.cells;
    }
}
