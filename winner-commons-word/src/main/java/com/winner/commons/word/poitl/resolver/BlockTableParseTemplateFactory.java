package com.winner.commons.word.poitl.resolver;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.GramerSymbol;
import com.deepoove.poi.template.run.RunTemplate;
import com.winner.commons.word.poitl.model.ExpressionModel;
import com.winner.commons.word.poitl.template.BlankTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * .
 *
 * @Classname ParseTemplateFactory
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/9 下午4:46
 */
@Slf4j
public class BlockTableParseTemplateFactory extends AbstractTemplateFactory {

    private Function<ExpressionModel, Class<Void>> callback;

    public BlockTableParseTemplateFactory(Function<ExpressionModel, Class<Void>> callback) {
       this.callback = callback;
    }

    @Override
    public RunTemplate createRunTemplate(Configure config, String tag, XWPFRun run) {
        log.info("The BlockTableParseTemplateFactory is beginning... and tag is {}", tag);

        ExpressionModel model = getExpressionModel(config.getIterable(), tag);
        if (null != model) {
            callback.apply(model);
        }

        return createBlankTemplate(config, tag, run);
    }

    private ExpressionModel getExpressionModel(Pair<Character, Character> iterable, String tag) {
        ExpressionModel model = null;
        char symbol = tag.charAt(0);
        if (!tagSet.contains(tag)) {
            // 若标签类型为"Spring表达式"区块对
            if ((symbol == iterable.getLeft() && isIncluded(tag))) {
                model = ExpressionModel.of().name(getSpELTagName(symbol, tag)).type(ExpressionType.BLOCK_SPEL.name()).expression(tag);
            }
            // 若标签类型为夸列区块对(表格)
            else if (symbol == iterable.getLeft() && !setColspanTag(symbol, tag)) {
                model = ExpressionModel.of().name(tag.replace(String.valueOf(symbol), "")).type(ExpressionType.BLOCK_TABLE.name());
            }
            // 图标标签
            else if (symbol == GramerSymbol.IMAGE.getSymbol()) {
                model = ExpressionModel.of().name(tag.replace(String.valueOf(symbol), "")).type(ExpressionType.IMAGE.name());
            }
            // 列表标签
            else if (symbol == GramerSymbol.NUMBERING.getSymbol()) {
                model = ExpressionModel.of().name(tag.replace(String.valueOf(symbol), "")).type(ExpressionType.LIST.name());
            }
            // 表格标签
            else if (isColspanTag(tag)) {
                model = ExpressionModel.of().name(tag).type(ExpressionType.BLOCK_TABLE.name());
            }
            else if (symbol == iterable.getRight()) {

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
