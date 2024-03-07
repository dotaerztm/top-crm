package com.lzj.admin.entity;

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
 * 视频表详情表
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_video_detail")
@ApiModel(value="视频详情信息", description="视频表详情表")
public class VideoDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父name")
    private String parentName;

    @ApiModelProperty(value = "视频名称")
    private String fileName;

    @ApiModelProperty(value = "类型")
    private Integer fileType;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "分组")
    private String fileGroup;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "新增时间")
    private Date insertTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除")
    private Integer isDel;

}
