package com.lzj.admin.service;

import com.lzj.admin.entity.AppletWorksLike;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.po.AppletWorksLikeParam;

/**
 * <p>
 * 小程序作品点赞表 服务类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
public interface IAppletWorksLikeService extends IService<AppletWorksLike> {

    void worksLike(AppletWorksLikeParam param);
}
