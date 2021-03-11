/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winnerinf.base.controller</p>
 * <p>File:BaseController.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年2月5日 下午12:08:09
 */
package com.winner.commons.base.controller;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.service.IService;
import com.winner.commons.base.model.ApiResponse;
import com.winner.commons.base.query.BaseQuery;
import com.winner.commons.base.query.PageWrapper;
import com.winner.commons.base.util.ReflectUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年2月5日 下午12:08:09
 * @version 1.0.2021
 */
@Slf4j
public abstract class BaseController<S extends IService<E>, E extends Model<E>, Q extends BaseQuery<E>> implements ApplicationContextAware {

	@GetMapping("/page")
	@ApiOperation(value = "分页查询")
	public ApiResponse page(final PageWrapper page, final Q query) {
		try {
			return ApiResponse.ok().data(service.page(page.createPage(), query.createQuery()));
		} catch (Exception e) {
			return ApiResponse.error(e);
		}
	}

	@GetMapping("/all")
	@ApiOperation(value = "普通查询")
	public ApiResponse list(final Q query) {
		try {
			return ApiResponse.ok().data(service.list(query.createQuery()));
		} catch (Exception e) {
			return ApiResponse.error(e);
		}
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "获取详情")
	public ApiResponse detail(@PathVariable final Serializable id) {
		try {
			return ApiResponse.ok().data(service.getById(id));
		} catch (Exception e) {
			return ApiResponse.error(e);
		}
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "逻辑删除")
	public ApiResponse remove(@PathVariable final Serializable id) {
		try {
			return service.removeById(id) ? ApiResponse.ok() : ApiResponse.of("删除失败！").error();
		} catch (Exception e) {
			return ApiResponse.error(e);
		}
	}

	@PostMapping
	@ApiOperation(value = "保存数据")
	public ApiResponse save(@RequestBody final E entity) {
		try {
			return service.saveOrUpdate(entity) ? ApiResponse.ok() : ApiResponse.of("保存失败！").error();
		} catch (Exception e) {
			return ApiResponse.error(e);
		}
	}
	
	private S service;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		setService(applicationContext);
	}
	
	private void setService(ApplicationContext applicationContext) {
		Class<S> requiredType = ReflectUtil.getGenericType(this.getClass());
		this.service = applicationContext.getBean(requiredType);
		log.info("The Mybatis's IService interface is implemented: {}", this.service);
	}
}
