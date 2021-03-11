/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winnerinf.commons.word.resolver</p>
 * <p>File:WordElementTemplateFactory.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年2月23日 上午11:59:55
 */
package com.winner.commons.word.poitl.resolver;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.template.run.RunTemplate;
import com.winner.commons.word.poitl.template.TableTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * <p>描述: 解决区块对表格不被引擎识别的问题</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年2月23日 上午11:59:55
 * @version 1.0.2021
 */
@Slf4j
public class BlockTableTemplateFactory extends AbstractTemplateFactory {

	@Override
	public RunTemplate createRunTemplate(Configure config, String tag, XWPFRun run) {
		log.info("The BlockTableTemplateFactory is beginning... and tag is {}", tag);

		return isIterableAndNormal(config.getIterable(), tag) ? super.createRunTemplate(config, tag, run) : createTableTemplate(config, tag, run);
	}
	
	private TableTemplate createTableTemplate(Configure config, String tag, XWPFRun run) {
		TableTemplate template = new TableTemplate(config, tag, run);
		template.setSource(config.getGramerPrefix() + tag + config.getGramerSuffix());
		template.setSign(EMPTY_CHAR);
		return template;
	}

}
