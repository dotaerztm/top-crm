package com.lzj.admin.po;


import lombok.Data;

import java.io.Serializable;

@Data
public class ImageAndVideoParam extends Pagination implements Serializable {

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

    //排序
    private Integer sort;

    //作者
    private String author;

    //作者
    private String authorImage;

    //时间
    private String time;

    //详情图url
    private String detailPath;

    //类型标志 0:图片 1:视频
    private Integer typeFlag;

}
