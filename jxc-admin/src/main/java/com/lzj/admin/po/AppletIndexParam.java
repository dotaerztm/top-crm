package com.lzj.admin.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
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

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date endTime;

}
