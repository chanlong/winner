package com.winner.commons.word.poitl.template;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.RenderPolicy;
import com.deepoove.poi.render.processor.Visitor;
import com.deepoove.poi.template.run.RunTemplate;
import com.winner.commons.word.poitl.policy.BlankTemplateRenderPolicy;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * 空模版.
 *
 * @Classname ParseTemplate
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/9 下午5:13
 */
public class BlankTemplate extends RunTemplate {

    public BlankTemplate(String tag, XWPFRun run) {
        this.tagName = tag;
        this.run = run;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public RenderPolicy findPolicy(Configure config) {
        return new BlankTemplateRenderPolicy();
    }
}
