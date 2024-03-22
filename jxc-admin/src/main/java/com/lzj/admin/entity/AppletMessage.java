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
 * 小程序信息通知表
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_applet_message")
@ApiModel(value="AppletMessage对象", description="小程序信息通知表")
public class AppletMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户唯一标识")
    private String uuid;

    @ApiModelProperty(value = "对方用户唯一标识")
    private String targetUuid;

    @ApiModelProperty(value = "圈子缩略图")
    private String imageUrl;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "跳转链接")
    private String link;

    @ApiModelProperty(value = "通知类型 0:粉丝记录 1:汽水收到 2:汽水赠送 3:系统通知 4:活动推送")
    private Integer messageType;

    @ApiModelProperty(value = "新增时间")
    private Date insertTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "0:未读  1:已读")
    private Integer isRead;

}
