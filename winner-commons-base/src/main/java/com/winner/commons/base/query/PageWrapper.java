/**
 * <p>Copyright:Copyright(c) 2021</p>
 * <p>Company:Professional</p>
 * <p>Package:com.winnerinf.base.query</p>
 * <p>File:PageModel.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2021年2月5日 下午2:38:22
 */
package com.winner.commons.base.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diboot.core.vo.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>描述: 分页包装器 </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2021年2月5日 下午2:38:22
 * @version 1.0.2021
 */
@Data
@ApiModel
public class PageWrapper implements Serializable {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "当前页", position = 98)
	private Integer currentPage = 1;
	
	@ApiModelProperty(value = "页大小", position = 99)
	private Integer pageSize = 10;

	public Pagination pagination() {
		Pagination page = new Pagination();
		page.setPageSize(pageSize);
		page.setPageIndex(currentPage);
		return page;
	}
}
