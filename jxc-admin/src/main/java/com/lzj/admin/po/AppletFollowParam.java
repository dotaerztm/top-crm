package com.lzj.admin.po;

import com.lzj.admin.entity.AppletWorksImage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 小程序作品表
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
@Data
public class AppletFollowParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "用户唯一标识")
    private String uuid;

    @ApiModelProperty(value = "关注用户唯一标识")
    private String followUuid;


}
