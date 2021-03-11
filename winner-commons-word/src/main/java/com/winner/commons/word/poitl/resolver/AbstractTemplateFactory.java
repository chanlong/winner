package com.winner.commons.word.poitl.resolver;

import cn.hutool.core.util.StrUtil;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.GramerSymbol;
import com.deepoove.poi.resolver.DefaultElementTemplateFactory;
import com.winner.commons.word.poitl.model.ExpressionModel;
import com.winner.commons.word.poitl.template.BlankTemplate;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.HashSet;
import java.util.Set;

/**
 * .
 *
 * @Classname AbstractTemplateFactory
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/9 下午4:48
 */
public abstract class AbstractTemplateFactory extends DefaultElementTemplateFactory {

    protected BlankTemplate createBlankTemplate(Configure config, String tag, XWPFRun run) {
        BlankTemplate template = new BlankTemplate(tag, run);
        template.setSource(config.getGramerPrefix() + tag + config.getGramerSuffix());
        template.setSign(EMPTY_CHAR);
        return template;
    }

    protected enum ExpressionType {
        TEXT, LIST, IMAGE, TABLE, BLOCK, BLOCK_SPEL, BLOCK_TABLE
    }

    protected static final String[] OPERATORS = {"!==", "!=", "==", "<=", ">=", "<", ">"};

    protected boolean isIterableAndNormal(Pair<Character, Character> iterable, String tag) {
        char symbol = tag.charAt(0);
        // 若标签类型为区块对
        if ((symbol == iterable.getLeft() && isIncluded(tag)) || (symbol == iterable.getRight() && tag.length() == 1)) {
            return true;
        }
        // 若标签类型为夸列区块对(表格)
        else if (symbol == iterable.getLeft() || symbol == iterable.getRight()) {
            return setColspanTag(symbol, tag);
        }
        // 其他标签
        else {
            return !isColspanTag(tag);
        }
    }

    protected Boolean isIncluded(String tag) {
        for (String op : OPERATORS) {
            if (tag.contains(op)) return true;
        }
        return false;
    }

    protected Boolean isColspanTag(String tag) {
        return StrUtil.isNotEmpty(colspanTag) && tag.startsWith(colspanTag);
    }

    protected Boolean setColspanTag(Character symbol, String tag) {
        this.colspanTag = tag.replace(String.valueOf(symbol), "");
        return false;
    }

    protected String getSpELTagName(Character symbol, String tag) {
        for (String op : OPERATORS) {
            if (tag.contains(op)) {
                return tag.split(op)[0].replace(String.valueOf(symbol), "").trim();
            }
        }
        return tag;
    }

    private String colspanTag;
}
