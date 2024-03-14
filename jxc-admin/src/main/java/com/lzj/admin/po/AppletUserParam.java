package com.lzj.admin.po;

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

    private String nickName;

    private String headImgUrl;

    private Date insertTime;

    private String mobile;

    private String code;

}
