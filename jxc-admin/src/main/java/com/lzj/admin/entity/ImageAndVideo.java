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
 * 图片和视频综合表
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_image_and_video")
@ApiModel(value="图片视频信息", description="图片和视频综合表")
public class ImageAndVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "类型")
    private Integer fileType;

    @ApiModelProperty(value = "文件类型 0:图片 1:视频")
    private Integer typeFlag;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "是否删除")
    private Integer isDel;

    @ApiModelProperty(value = "图片名称")
    private String fileName;

    @ApiModelProperty(value = "外链地址")
    private String link;

    @ApiModelProperty(value = "分组")
    private String fileGroup;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "新增时间")
    private Date insertTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "作者头像")
    private String authorImage;

    @ApiModelProperty(value = "时间")
    private String fileTime;

    @ApiModelProperty(value = "时间")
    private Integer isMore;

}
