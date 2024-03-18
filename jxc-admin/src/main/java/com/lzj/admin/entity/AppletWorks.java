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
 * 小程序作品表
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_applet_works")
@ApiModel(value="AppletWorks对象", description="小程序作品表")
public class AppletWorks implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户唯一标识")
    private String uuid;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "点赞数")
    private Integer likeCount;

    @ApiModelProperty(value = "作品状态 0:未审核 1:已审核")
    private Integer worksStatus;

    @ApiModelProperty(value = "作品类型 0:普通 1:官方")
    private Integer worksType;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "新增时间")
    private Date insertTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "0:正常 1:已删除")
    private Integer isDel;

    @ApiModelProperty(value = "作品缩略图")
    private String imageUrl;

}
