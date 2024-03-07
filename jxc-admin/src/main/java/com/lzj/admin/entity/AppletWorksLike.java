package com.lzj.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 小程序作品点赞表
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_applet_works_like")
@ApiModel(value="AppletWorksLike对象", description="小程序作品点赞表")
public class AppletWorksLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "作品id")
    private String worksId;

    @ApiModelProperty(value = "用户唯一标识")
    private String uuid;

    @ApiModelProperty(value = "点赞用户uuid")
    private String likeUuid;

    @ApiModelProperty(value = "点赞状态 1:点赞 0:取消点赞")
    private Integer likeStatus;

    @ApiModelProperty(value = "新增时间")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
