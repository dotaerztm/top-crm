package com.lzj.admin.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@ApiModel("分页参数")
public class Pagination implements Serializable {
	private static final long serialVersionUID = 1L;
	public interface QueryByPage{}

	@ApiModelProperty("页码")
	private Integer pageIndex;

	@ApiModelProperty("条数")
	@Max(value = 100, message = "crm.global.page-size-error", groups = {QueryByPage.class})
	private Integer pageSize;

	@ApiModelProperty(value = "行数", hidden = true)
	private Integer limit;

	@ApiModelProperty(value = "偏移行数", hidden = true)
	private Integer offset;

	public Integer getLimit() {
		return pageSize;
	}

	public Integer getPageIndex() {
		if(pageIndex==null)pageIndex=1;
		return pageIndex;
	}

	public Integer getPageSize() {
		if(pageSize==null)pageSize=20;
		return pageSize;
	}


	public Integer getOffset() {
		if(pageIndex==null)pageIndex=1;
		if(pageSize==null)pageSize=20;
		return (pageIndex - 1) * pageSize;
	}
}
