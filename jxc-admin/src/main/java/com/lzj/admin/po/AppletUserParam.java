package com.lzj.admin.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信授权
 *
 */
@Data
public class AppletUserParam extends Pagination implements Serializable {


    private Integer id;

    private String openid;

    private String uuid;

    private String nickName;

    private String headImgUrl;

    private String backgroundImage;

    private String birthday;

    private String sex;

    private String nativePlace;

    private String address;

    private String wechat;

    private Date insertTime;

    private String mobile;

    private String code;

}
