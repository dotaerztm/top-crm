package com.lzj.admin.po;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TokenPO extends Pagination implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    //accessToken
    private String access_token;

    //openId
    private String openid;

    //昵称
    private String nickname;

    //头像
    private String headimgurl;

    //性别
    private Integer sex;

    //SDK  ticket
    private String ticket;

    private String errcode;

    private String errmsg;

}
