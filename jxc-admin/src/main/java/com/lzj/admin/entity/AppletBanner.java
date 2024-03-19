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
 * 小程序banner表
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_applet_banner")
@ApiModel(value="AppletBanner对象", description="小程序banner表")
public class AppletBanner implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "关注用户唯一标识")
    private String imageUrl;

    @ApiModelProperty(value = "权重")
    private Integer sort;

    @ApiModelProperty(value = "关注状态 0:未发布 1:已发布")
    private Integer bannerStatus;

    @ApiModelProperty(value = "生效-开始时间")
    private Date startTime;

    @ApiModelProperty(value = "生效-结束时间")
    private Date endTime;

    @ApiModelProperty(value = "新增时间")
    private Date insertTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
