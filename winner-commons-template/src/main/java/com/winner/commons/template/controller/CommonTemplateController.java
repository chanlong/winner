package com.winner.commons.template.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.deepoove.poi.config.ConfigureBuilder;
import com.winner.commons.base.enums.PolicyType;
import com.winner.commons.base.model.ApiResponse;
import com.winner.commons.base.model.FileModel;
import com.winner.commons.base.query.PageWrapper;
import com.winner.commons.template.entity.CommonTemplate;
import com.winner.commons.template.entity.CommonTemplateFile;
import com.winner.commons.template.entity.CommonTemplateModel;
import com.winner.commons.template.query.CommonTemplateQuery;
import com.winner.commons.template.service.CommonTemplateFileService;
import com.winner.commons.template.service.CommonTemplateModelService;
import com.winner.commons.template.service.CommonTemplateService;
import com.winner.commons.word.poitl.model.WordDataModel;
import com.winner.commons.word.poitl.resolver.BlankTemplateFactory;
import com.winner.commons.word.poitl.resolver.BlockTableParseTemplateFactory;
import com.winner.commons.word.poitl.resolver.BlockTableTemplateFactory;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * .
 *
 * @Classname CommmonTemplateController
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/10 下午2:43
 */
public abstract class CommonTemplateController {

    @Autowired
    private CommonTemplateService service;

    @Autowired
    private CommonTemplateFileService fileService;

    @Autowired
    private CommonTemplateModelService modelService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public ApiResponse page(final PageWrapper page, final CommonTemplateQuery query) {
        try {
            return ApiResponse.ok().data(service.getEntityList(query.createQuery(), page.pagination()));
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
    }

    @GetMapping("/all")
    @ApiOperation(value = "普通查询")
    public ApiResponse list(final CommonTemplateQuery query) {
        try {
            return ApiResponse.ok().data(service.getEntityList(query.createQuery()));
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取详情")
    public ApiResponse detail(@PathVariable final Serializable id) {
        try {
            return ApiResponse.ok().data(service.getEntity(id));
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "逻辑删除")
    public ApiResponse remove(@PathVariable final Serializable id) {
        try {
            return service.deleteEntity(id) ? ApiResponse.ok() : ApiResponse.of("删除失败！").error();
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
    }

    @PostMapping
    @ApiOperation(value = "保存模版")
    public ApiResponse save(@RequestBody final CommonTemplate entity) {
        try {
            return service.createOrUpdateEntity(entity) ? ApiResponse.ok() : ApiResponse.of("保存失败！").error();
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
    }

    @GetMapping("/file/all/{templateId}")
    @ApiOperation(value = "文件列表")
    public ApiResponse list(@PathVariable String templateId) {
        try {
            LambdaQueryWrapper<CommonTemplateFile> queryWrapper = Wrappers.lambdaQuery();
            List<CommonTemplateFile> result = fileService.getEntityList(queryWrapper.eq(CommonTemplateFile::getTemplateId, templateId));
            return ApiResponse.ok().data(result);
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
    }

    @DeleteMapping("/file/{id}")
    @ApiOperation(value = "文件删除")
    public ApiResponse delete(@PathVariable String id) {
        try {
            return ApiResponse.ok().data(fileService.delete(id));
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
    }

    @PutMapping("/file/{templateId}")
    @ApiOperation(value = "上传文件")
    public ApiResponse upload(@PathVariable String templateId, MultipartFile file) {
        try {
            CommonTemplate template = service.getEntity(templateId);
            if (null != template) {
                CommonTemplateFile templateFile = fileService.upload(FileModel.of(file).policy(PolicyType.DATETIME), template);
                fileService.builder(builder(false, templateFile)).parse(templateFile);
                return ApiResponse.ok().data(templateFile);
            } else {
                return ApiResponse.of("未发现指定的模版！").error();
            }
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
    }

    @GetMapping("/file/export/{templateId}")
    @ApiOperation(value = "模版导出")
    public ApiResponse export(@PathVariable String templateId) {
        try {
            return ApiResponse.ok().data(fileService.builder(builder(true, null)).export(templateId, dataModel()));
        } catch (Exception e) {
            return ApiResponse.error(e);
        }
    }

    private ConfigureBuilder builder(boolean isRender, CommonTemplateFile templateFile) {
        // 获取poi-pl默认配置构造器
        ConfigureBuilder builder = builder();

        // 非默认标签时
        if (!isDefault) {
            if (isRender) {
                // 设置模版工厂,解决表格区块对不识别的问题
                builder.setElementTemplateFactory(new BlockTableTemplateFactory());
            } else {
                // 设置模版工厂,用于解析并保存模版表达式的模型
                builder.setElementTemplateFactory(new BlockTableParseTemplateFactory(model -> {
                    CommonTemplateModel entity = CommonTemplateModel.of().tag(model.name())
                                                                         .type(model.type())
                                                                         .expression(model.expression())
                                                                         .templateId(templateFile.getTemplateId())
                                                                         .templateFileId(templateFile.getId());
                    modelService.createOrUpdateEntity(entity);
                    return Void.TYPE;
                }));
            }
        } else if (!isRender) {
            // 设置模版工厂,用于解析并保存模版表达式的模型
            builder.setElementTemplateFactory(new BlankTemplateFactory(model -> {
                CommonTemplateModel entity = CommonTemplateModel.of().tag(model.name())
                                                                     .type(model.type())
                                                                     .expression(model.expression())
                                                                     .templateId(templateFile.getTemplateId())
                                                                     .templateFileId(templateFile.getId());
                modelService.createOrUpdateEntity(entity);
                return Void.TYPE;
            }));
        }

        return builder;
    }

    /**
     * poi-tl ConfigureBuilder.
     *
     * @description TODO
     * @return com.deepoove.poi.config.ConfigureBuilder
     * @author chanlong
     * @date 2021/3/10 下午3:33
     */
    protected abstract ConfigureBuilder builder();

    protected abstract WordDataModel dataModel();

    @Setter
    private boolean isDefault;
}
