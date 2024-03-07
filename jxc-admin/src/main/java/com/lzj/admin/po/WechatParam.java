package com.lzj.admin.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信授权
 *
 */
@Data
public class WechatParam  extends Pagination implements Serializable {


    private Integer id;

    private String openid;

    private String nickName;

    private String headImgUrl;

    private String forwardImgUrl;

    private String socialImgUrl;

    private Integer isStudent;

    private Date insertTime;

    private String mobile;

}
