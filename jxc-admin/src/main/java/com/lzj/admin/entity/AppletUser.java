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
 * 小程序用户表
 * </p>
 *
 * @author ch_ztm
 * @since 2024-02-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_applet_user")
@ApiModel(value="AppletUser对象", description="小程序用户表")
public class AppletUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户唯一标识")
    private String uuid;

    private String nickName;

    @ApiModelProperty(value = "头像")
    private String headImgUrl;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    @ApiModelProperty(value = "所在地")
    private String address;

    @ApiModelProperty(value = "公司")
    private String company;

    @ApiModelProperty(value = "职位")
    private String job;

    @ApiModelProperty(value = "微信号")
    private String wechat;

    @ApiModelProperty(value = "类型 0:普通用户 1:讲师")
    private Integer userType;

    @ApiModelProperty(value = "新增时间")
    private Date insertTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "背景图片")
    private String backgroundImage;


}
