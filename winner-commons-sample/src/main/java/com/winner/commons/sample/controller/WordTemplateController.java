package com.winner.commons.sample.controller;

import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.winner.commons.template.controller.CommonTemplateController;
import com.winner.commons.word.poitl.data.ColspanCells;
import com.winner.commons.word.poitl.data.ColspanRows;
import com.winner.commons.word.poitl.model.WordDataModel;
import com.winner.commons.word.poitl.util.WordUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 *
 * @Classname WordTemplateController
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/5 上午11:24
 */
@Api(tags = "样例应用-模版管理")
@RestController
@RequestMapping("/template")
public class WordTemplateController extends CommonTemplateController {

    @Override
    protected ConfigureBuilder builder() {
        // 设置 poi-tl 是否使用默认标签
        setDefault(false);

        // 设置 poi-tl ConfigureBuilder
        return WordUtil.createBuilder().addPlugin('$', new TableRenderPolicy()).buidIterableLeft('#');
    }

    /**
     * 设置数据模型（要导出的数据）.
     * @description TODO
     * @return com.winner.commons.word.poitl.model.WordDataModel
     * @author chanlong
     * @date 2021/3/11 上午8:48
     */
    @Override
    protected WordDataModel dataModel() {
        return new WordDataModel() {{
            putData("FRXM", "张三");
            putData("SFWT", 1);
            putData("WTRXM", "李四");
            putData("CLQK", ColspanRows.create(
                ColspanCells.of().put("CLQK_CPH", "沪A9B870").put("CLQK_XSZDJRQ", "2020-02-18").put("CLQK_ZZL", "0.6").put("CLQK_HDZZL", "0.5").put("CLQK_DPRQ", "2020-02-18").put("CLQK_DJ_TEXT", "A级").put("CLQK_CLJYFW_TEXT", "运输").create(),
                ColspanCells.of().put("CLQK_CPH", "沪A9B870").put("CLQK_XSZDJRQ", "2020-02-18").put("CLQK_ZZL", "0.6").put("CLQK_HDZZL", "0.5").put("CLQK_DPRQ", "2020-02-18").put("CLQK_DJ_TEXT", "A级").put("CLQK_CLJYFW_TEXT", "运输").create()
            ));
        }};
    }
}
