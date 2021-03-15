package com.winner.commons.template.service.impl;

import com.diboot.core.service.impl.BaseServiceImpl;
import com.winner.commons.template.entity.CommonTemplate;
import com.winner.commons.template.mapper.CommonTemplateMapper;
import com.winner.commons.template.service.CommonTemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * .
 *
 * @Classname TemplateServiceImpl
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/2/24 下午2:58
 */
@Service
@Transactional
public class CommonTemplateServiceImpl extends BaseServiceImpl<CommonTemplateMapper, CommonTemplate> implements CommonTemplateService {

}
