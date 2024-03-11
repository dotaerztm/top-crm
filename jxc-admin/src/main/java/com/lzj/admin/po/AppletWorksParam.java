package com.lzj.admin.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lzj.admin.entity.AppletWorksImage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
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
public class AppletWorksParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "用户唯一标识")
    @NotBlank(message = "用户标识不能为空")
    private String uuid;

    @ApiModelProperty(value = "标题")
    @Length(max = 20, message = "标题最长20个字符")
    private String title;

    @ApiModelProperty(value = "内容")
    @Length(max = 300, message = "正文最长300个字符")
    private String content;

    @ApiModelProperty(value = "点赞数")
    private Integer likeCount;

    @ApiModelProperty(value = "作品状态 0:未审核 1:已审核")
    private Integer worksStatus;

    @ApiModelProperty(value = "作品类型 0:普通 1:官方")
    private Integer worksType;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "作品图片详情集合")
    List<AppletWorksImage> worksImageList;

}
