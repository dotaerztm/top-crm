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
 * 小程序作品图片表
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_applet_works_image")
@ApiModel(value="AppletWorksImage对象", description="小程序作品图片表")
public class AppletWorksImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "作品id")
    private String worksId;

    @ApiModelProperty(value = "用户唯一标识")
    private String uuid;

    @ApiModelProperty(value = "图片url")
    private String imageUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "新增时间")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
