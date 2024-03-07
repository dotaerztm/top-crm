package com.lzj.admin.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ImagePO extends Pagination implements Serializable {

    private static final long serialVersionUID = 1L;

    //id
    private Integer id;

    //url
    private String url;

    //类型
    private Integer fileType;

    //备注
    private String remarks;

    //是否删除
    private Integer isDel;

    //图片名称
    private String fileName;

    //外链地址
    private String link;

    //分组
    private String fileGroup;

    //职位
    private String job;

    //排序
    private Integer sort;

    //新增时间
    private Date insertTime;

    //修改时间
    private Date updateTime;

    //作者
    private String author;

    //时间
    private String fileTime;

    //缩略图
    private String thumbnail;

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
