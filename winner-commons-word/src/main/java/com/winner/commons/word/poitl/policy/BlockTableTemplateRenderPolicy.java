/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winnerinf.modules.word.plugins</p>
 * <p>File:WordTextRenderPolicy.java</p>
 * <p>类更新历史信息</p>
 *
 * @todo chanlong(陈龙) 创建于 2021年2月20日 上午10:37:55
 */
package com.winner.commons.word.poitl.policy;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.AbstractRenderPolicy;
import com.deepoove.poi.render.RenderContext;
import com.deepoove.poi.template.ElementTemplate;
import com.winner.commons.word.poitl.data.ColspanCellRenderData;
import com.winner.commons.word.poitl.data.ColspanRowRenderData;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>描述: 区块对表格模版渲染策略</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年2月20日 上午10:37:55
 * @version 1.0.2021
 */
@Slf4j
@NoArgsConstructor
public class BlockTableTemplateRenderPolicy extends AbstractRenderPolicy<ColspanRowRenderData> {

    private Configure config;

    @Override
    public void doRender(RenderContext<ColspanRowRenderData> context) throws Exception {
        config = context.getConfig();
        XWPFRun run = context.getRun();
        ColspanRowRenderData data = context.getData();
        ElementTemplate eleTemplate = context.getEleTemplate();
        BlockTableTemplate blockTemplate = getBlockTemplate(run);

        char sign = eleTemplate.getSource().charAt(1);
        if (null != data && sign == config.getIterable().getLeft()) {
            List<ColspanCellRenderData> renderData = data.getCells();
            for (int i = 0, size = renderData.size(); i < size; i++) {
                // 设置当前行定位
                blockTemplate.setPosition(i);

                // 获取或创建模版中的下一行
                XWPFTableRow nextRow = blockTemplate.getOrCreateNextRow();
                // 获取渲染数据
                ColspanCellRenderData cellData = renderData.get(i);
                // 渲染数据
                blockTemplate.setCellValue(nextRow, cellData);
                // 添加新行
                blockTemplate.addNewRow(nextRow);
            }
        } else if (sign == config.getIterable().getRight()) {
            // 删除模版行
            blockTemplate.removeTemplateRow();
        }
    }

    // 获取区块对表格模版
    private BlockTableTemplate getBlockTemplate(XWPFRun run) {
        XWPFTableCell templateCell = (XWPFTableCell) ((XWPFParagraph) run.getParent()).getBody();
        XWPFTableRow templateRow = templateCell.getTableRow();
        XWPFTable templateTable = templateRow.getTable();

        // 创建区块对表格模版并设置模版基本属性
        BlockTableTemplate blockTemplate = new BlockTableTemplate(templateRow, templateTable);
        blockTemplate.setCellTags();
        blockTemplate.setCellSize();
        blockTemplate.setRowIndex();
        blockTemplate.setRowSize();

        return blockTemplate;
    }

    /** 区块对表格模版 */
    private class BlockTableTemplate {
        private final XWPFTableRow templateRow;
        private final XWPFTable templateTable;

        /** 模版行单元格标签集合 */
        private List<String> cellTags = new ArrayList<>();

        /** 模版行的行号 */
        private int rowIndex;

        /** 模版行单元格数 */
        private int cellSize;

        /** 不包含模版行的行数 */
        private int rowSize;

        private int position;

        public BlockTableTemplate(XWPFTableRow row, XWPFTable table) {
            this.templateTable = table;
            this.templateRow = row;
        }

        /** 获取行索引 */
        public int getRowIndex(XWPFTableRow row) {
            return templateTable.getRows().indexOf(row);
        }

        /** 获取或创建新行 */
        public XWPFTableRow getOrCreateNextRow() throws IOException, XmlException {
            XWPFTableRow tableRow = templateTable.getRow(position);
            return null == tableRow ? createNewRow(position) : tableRow;
        }

        /** 复制上一行的样式，创建新行 */
        public XWPFTableRow createNewRow(int position) throws IOException, XmlException {
            // 获取上一行
            CTRow ctrow = CTRow.Factory.parse(templateTable.getRow(position - 1).getCtRow().newInputStream());
            // 依据上一行创建新行
            return new XWPFTableRow(ctrow, templateTable);
        }

        /** 添加新行 */
        public void addNewRow(XWPFTableRow nextRow) {
            if (getRowIndex(nextRow) < 0) {
                templateTable.addRow(nextRow, position);
            }
        }

        /** 删除模版行 */
        public void removeTemplateRow() {
            templateTable.removeRow(rowIndex);
        }

        /** 设置单元格标签集合 */
        public void setCellTags() {
            List<XWPFTableCell> cells = templateRow.getTableCells();
            cellTags = cells.stream().map(cell -> getTagName(cell.getText())).collect(Collectors.toList());
        }

        /** 设置单元格值 */
        public void setCellValue(XWPFTableRow row, ColspanCellRenderData data) {
            Map<String, Object> map = data.getData();
            List<XWPFTableCell> cells = row.getTableCells();
            for (int i = 0, size = cells.size(); i < size; i++) {
                String key = cellTags.get(i);
                Object value = map.get(key);
                if (ObjectUtil.isNotEmpty(value)) {
                    XWPFTableCell cell = cells.get(i);
                    CTTc cttc = cell.getCTTc();
                    CTP ctp = (cttc.sizeOfPArray() == 0) ? cttc.addNewP() : cttc.getPArray(0);
                    List<CTR> ctrs = ctp.getRList();
                    if (ctrs.size() > 0) {
                        for (int j = 0; j < ctrs.size(); j++) {
                            ctrs.get(j).getTArray(0).setStringValue(j == 0 ? String.valueOf(value) : "");
                        }
                    } else {
                        cell.setText(String.valueOf(value));
                    }
                }
            }
        }

        /** 设置模版行单元格数 */
        public void setCellSize() {
            this.cellSize = templateRow.getTableCells().size();
        }

        /** 设置模版行行索引 */
        public void setRowIndex() {
            this.rowIndex = getRowIndex(templateRow);
        }

        /** 设置行数（不含模版行） */
        public void setRowSize() {
            List<XWPFTableRow> rows = templateTable.getRows();
            for (int i = rowIndex + 1, totalRows = rows.size(); i < totalRows; i++) {
                if (cellSize == rows.get(i).getTableCells().size()) rowSize++;
            }
        }

        public void setPosition(int dataIndex) {
            this.position = dataIndex + rowIndex + 1;
        }

        private String getTagName(String cellText) {
            if (cellText.contains(config.getGramerPrefix()) && cellText.contains(config.getGramerSuffix())) {
                if (cellText.lastIndexOf(config.getIterable().getRight()) > -1) {
                    return cellText.substring(cellText.indexOf(config.getGramerPrefix()) + 1, cellText.indexOf(config.getGramerSuffix()));
                } else {
                    return StrUtil.isEmpty(cellText) ? "" : cellText.substring(cellText.lastIndexOf(config.getGramerPrefix()) + 1, cellText.lastIndexOf(config.getGramerSuffix()));
                }
            } else {
                return cellText;
            }
        }
    }
}
