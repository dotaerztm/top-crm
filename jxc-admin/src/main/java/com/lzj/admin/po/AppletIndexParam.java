package com.lzj.admin.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 小程序首页param
 *
 */
@Data
public class AppletIndexParam extends Pagination implements Serializable {


    private Integer id;

    private Integer worksType;

    private String mobile;

}
