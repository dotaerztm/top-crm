package com.lzj.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 微信授权
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_wechat_login")
@ApiModel(value="微信授权", description="用户表")
public class Wechat implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "openid")
    private String openid;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String headImgUrl;

    @ApiModelProperty(value = "转发图片")
    private String forwardImgUrl;

    @ApiModelProperty(value = "朋友圈图片")
    private String socialImgUrl;

    @ApiModelProperty(value = "是否学员 0:否 1:是")
    private Integer isStudent;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "新增时间")
    private Date insertTime;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
