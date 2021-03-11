package com.winner.commons.word.poitl.resolver;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.GramerSymbol;
import com.deepoove.poi.template.run.RunTemplate;
import com.winner.commons.word.poitl.model.ExpressionModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * .
 *
 * @Classname BlankTemplateFactory
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/10 下午3:59
 */
@Slf4j
public class BlankTemplateFactory extends AbstractTemplateFactory {

    private Function<ExpressionModel, Class<Void>> callback;

    public BlankTemplateFactory(Function<ExpressionModel, Class<Void>> callback) {
        this.callback = callback;
    }

    @Override
    public RunTemplate createRunTemplate(Configure config, String tag, XWPFRun run) {
        log.info("The BlankTemplateFactory is beginning... and tag is {}", tag);

        ExpressionModel model = getExpressionModel(tag);
        if (null != model) {
            callback.apply(model);
        }

        return createBlankTemplate(config, tag, run);
    }

    private ExpressionModel getExpressionModel(String tag) {
        ExpressionModel model = null;
        char symbol = tag.charAt(0);
        if (!tagSet.contains(tag)) {
            if (symbol == GramerSymbol.BLOCK_END.getSymbol()) {

            }
            // Spring EL表达式
            else if ((symbol == GramerSymbol.ITERABLE_START.getSymbol() && isIncluded(tag))) {
                model = ExpressionModel.of().name(getSpELTagName(symbol, tag)).type(ExpressionType.BLOCK_SPEL.name()).expression(tag);
            }
            // 区块对标签
            else if (symbol == GramerSymbol.ITERABLE_START.getSymbol()) {
                model = ExpressionModel.of().name(tag.replace(String.valueOf(symbol), "")).type(ExpressionType.BLOCK.name()).expression(tag);
            }
            // 图标标签
            else if (symbol == GramerSymbol.IMAGE.getSymbol()) {
                model = ExpressionModel.of().name(tag.replace(String.valueOf(symbol), "")).type(ExpressionType.IMAGE.name());
            }
            // 表格标签
            else if (symbol == GramerSymbol.TABLE.getSymbol()) {
                model = ExpressionModel.of().name(tag.replace(String.valueOf(symbol), "")).type(ExpressionType.TABLE.name());
            }
            // 列表标签
            else if (symbol == GramerSymbol.NUMBERING.getSymbol()) {
                model = ExpressionModel.of().name(tag.replace(String.valueOf(symbol), "")).type(ExpressionType.LIST.name());
            }
            // 文本标签
            else {
                model = ExpressionModel.of().name(tag).type(ExpressionType.TEXT.name());
            }
        }
        tagSet.add(tag);
        return model;
    }

    private final Set<String> tagSet = new HashSet<>();
}
