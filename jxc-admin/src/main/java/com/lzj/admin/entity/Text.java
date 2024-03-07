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

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_text")
@ApiModel(value="文本信息", description="文本表")
public class Text implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "类型")
    private Integer textType;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "是否删除")
    private Integer isDel;

    @ApiModelProperty(value = "名称")
    private String textName;

    @ApiModelProperty(value = "外链地址")
    private String link;

    @ApiModelProperty(value = "分组")
    private String textGroup;

    @ApiModelProperty(value = "时间")
    private String textTime;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "新增时间")
    private Date insertTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "课程介绍 ClassTime内容")
    private String classTimeContent;

    @ApiModelProperty(value = "课程介绍 Assignment中部信息")
    private String assignmentMiddle;

    @ApiModelProperty(value = "课程介绍 Assignment内容")
    private String assignmentContent;

    @ApiModelProperty(value = "课程介绍 Assignment外链")
    private String assignmentLink;

}
