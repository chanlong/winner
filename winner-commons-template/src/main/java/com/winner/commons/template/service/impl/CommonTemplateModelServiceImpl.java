package com.winner.commons.template.service.impl;

import com.diboot.core.service.impl.BaseServiceImpl;
import com.winner.commons.template.entity.CommonTemplateModel;
import com.winner.commons.template.mapper.CommonTemplateModelMapper;
import com.winner.commons.template.service.CommonTemplateModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * .
 *
 * @Classname CommonTemplateModelServiceImpl
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/10 下午12:30
 */
@Service
@Transactional
public class CommonTemplateModelServiceImpl extends BaseServiceImpl<CommonTemplateModelMapper, CommonTemplateModel> implements CommonTemplateModelService {
}
