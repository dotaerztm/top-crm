package com.lzj.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 公共返回对象
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
	@ApiModelProperty(value = "响应状态")
	private long code;
	@ApiModelProperty(value = "响应消息")
	private String message;
	@ApiModelProperty(value = "响应结果信息")
	private Map<String, Object> data;


	/**
	 * 成功返回结果
	 * @param message
	 * @return
	 */
	public static RespBean success(String message){
		return new RespBean(200,message, null);
	}

	/**
	 * 成功返回结果
	 * @param message
	 * @param data
	 * @return
	 */
	public static RespBean success(String message,Map<String,Object> data){
		return new RespBean(200,message,data);
	}

	/**
	 * 失败返回结果
	 * @param message
	 * @return
	 */
	public static RespBean error(String message){
		return new RespBean(500,message,null);
	}

	/**
	 * 失败返回结果
	 * @param message
	 * @param data
	 * @return
	 */
	public static RespBean error(String message,Map<String,Object> data){
		return new RespBean(500,message,data);
	}
}