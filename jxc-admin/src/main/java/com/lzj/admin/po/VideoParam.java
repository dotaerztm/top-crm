package com.lzj.admin.po;


import lombok.Data;

import java.io.Serializable;

@Data
public class VideoParam extends Pagination implements Serializable {

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
}
