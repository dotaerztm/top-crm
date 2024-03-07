package com.lzj.admin.po;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

@Data
public class ImageParam extends Pagination implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    //file url
    private String path;

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

    //职位
    private String job;

    //排序
    private Integer sort;

    //作者
    private String author;

    //时间
    private String time;

    //缩略图
    private String thumbnail;


    //详情图url
    private String detailPath;

    //课程介绍图1
    private String Introduction1;

    //课程介绍图2
    private String Introduction2;

    //联系方式 图片url
    private String contactUrl;

    //课程介绍 ClassTime内容
    private String classTimeContent;

    //课程介绍 Assignment中部信息
    private String assignmentMiddle;

    //课程介绍 Assignment内容
    private String assignmentContent;

    //课程介绍 Assignment外链
    private String assignmentLink;

}
