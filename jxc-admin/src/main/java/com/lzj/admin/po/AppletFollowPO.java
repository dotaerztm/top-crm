package com.lzj.admin.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 小程序用户关注表
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-12
 */
@Data
public class AppletFollowPO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "id")
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

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String headImgUrl;
}
