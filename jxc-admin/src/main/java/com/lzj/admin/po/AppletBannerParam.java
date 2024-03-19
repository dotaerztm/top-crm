package com.lzj.admin.po;

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
 * 小程序banner表
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-19
 */
@Data
public class AppletBannerParam implements Serializable {


    @ApiModelProperty(value = "主键id")
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
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "生效-结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "新增时间")
    private Date insertTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
