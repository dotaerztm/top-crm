package com.lzj.admin.po;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TextParam extends Pagination implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    //内容
    private String content;

    //类型
    private Integer type;

    //备注
    private String remarks;

    //名称
    private String name;

    //外联
    private String link;

    //分组
    private String group;

    //时间
    private String time;

    //排序
    private Integer sort;

    //作者
    private String author;

    //课程介绍 ClassTime标题
    private String classTimeTitle;

    //课程介绍 ClassTime中部信息
    private String classTimeMiddle;

    //课程介绍 ClassTime内容
    private String classTimeContent;

    //课程介绍 Assignment标题
    private String assignmentTitle;

    //课程介绍 Assignment中部信息
    private String assignmentMiddle;

    //课程介绍 Assignment内容
    private String assignmentContent;

    //课程介绍 Assignment外链
    private String assignmentLink;

    //新name
    private String newName;
}
