package com.lzj.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 小程序用户关注表
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_applet_follow")
@ApiModel(value="AppletFollow对象", description="小程序用户关注表")
public class AppletFollow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户唯一标识")
    private String uuid;

    @ApiModelProperty(value = "关注用户唯一标识")
    private String followUuid;

    @ApiModelProperty(value = "关注状态 0:关注 1:已关注 2:互相关注")
    private Integer followStatus;

    @ApiModelProperty(value = "新增时间")
    private Date  insertTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


}
