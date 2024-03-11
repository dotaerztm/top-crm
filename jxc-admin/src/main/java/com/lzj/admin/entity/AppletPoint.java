package com.lzj.admin.entity;

import java.math.BigDecimal;
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
 * 小程序-用户积分表
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_applet_point")
@ApiModel(value="AppletPoint对象", description="小程序-用户积分表")
public class AppletPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户唯一标识")
    private String uuid;

    @ApiModelProperty(value = "当前积分")
    private BigDecimal pointCurrent;

    @ApiModelProperty(value = "总积分(只加不减)")
    private BigDecimal pointSum;

    @ApiModelProperty(value = "新增时间")
    private Date insertTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


}
