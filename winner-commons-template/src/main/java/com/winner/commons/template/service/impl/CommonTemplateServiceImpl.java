package com.winner.commons.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winner.commons.template.entity.CommonTemplate;
import com.winner.commons.template.entity.CommonTemplateFile;
import com.winner.commons.template.mapper.CommonTemplateFileMapper;
import com.winner.commons.template.mapper.CommonTemplateMapper;
import com.winner.commons.template.service.CommonTemplateFileService;
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
public class CommonTemplateServiceImpl extends ServiceImpl<CommonTemplateMapper, CommonTemplate> implements CommonTemplateService {

}
