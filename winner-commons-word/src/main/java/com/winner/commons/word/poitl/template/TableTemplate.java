/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winnerinf.commons.word.template</p>
 * <p>File:TableTemplate.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年2月23日 下午1:50:57
 */
package com.winner.commons.word.poitl.template;

import cn.hutool.core.util.StrUtil;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.RenderPolicy;
import com.deepoove.poi.render.processor.Visitor;
import com.deepoove.poi.template.run.RunTemplate;
import com.winner.commons.word.poitl.policy.BlockTableTemplateRenderPolicy;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年2月23日 下午1:50:57
 * @version 1.0.2021
 */
public class TableTemplate extends RunTemplate {

	public TableTemplate(Configure config, String tagName, XWPFRun run) {
		this.tagName = getTagName(config.getIterable(), tagName);
		this.run = run;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public RenderPolicy findPolicy(Configure config) {
        RenderPolicy policy = config.getCustomPolicy(tagName);
        return null == policy ? new BlockTableTemplateRenderPolicy() : policy;
	}

	public String getTagName(Pair<Character, Character> pair, String tagName) {
		if (StrUtil.isNotEmpty(tagName)) {
			return tagName.replace(String.valueOf(pair.getLeft()),"").replace(String.valueOf(pair.getRight()),"");
		} else {
			return tagName;
		}
	}
}
