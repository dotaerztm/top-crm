package com.lzj.admin.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 小程序-用户积分详情
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_applet_point_detail")
@ApiModel(value="AppletPointDetail对象", description="小程序-用户积分详情")
public class AppletPointDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户唯一标识")
    private String uuid;

    @ApiModelProperty(value = "积分")
    private BigDecimal point;

    @ApiModelProperty(value = "积分类型")
    private Integer pointType;

    @ApiModelProperty(value = "积分状态 0:增加积分 1:减少积分")
    private Integer pointStatus;

    @ApiModelProperty(value = "课程id(兑换积分)")
    private Integer worksId;

    @ApiModelProperty(value = "兑换码(兑换积分)")
    private String redeemCode;

    @ApiModelProperty(value = "新增时间")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
