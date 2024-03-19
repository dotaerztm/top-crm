package com.lzj.admin.service;

import com.lzj.admin.entity.AppletBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletBannerParam;
import com.lzj.admin.po.AppletIndexParam;

/**
 * <p>
 * 小程序banner表 服务类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-19
 */
public interface IAppletBannerService extends IService<AppletBanner> {


    RespBean selectBannerByPage(AppletIndexParam param);

    void saveBanner(AppletBannerParam param);

    void updateBanner(AppletBannerParam param);

}
