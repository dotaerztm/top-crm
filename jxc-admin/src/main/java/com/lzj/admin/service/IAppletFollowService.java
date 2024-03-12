package com.lzj.admin.service;

import com.lzj.admin.entity.AppletFollow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.po.AppletFollowParam;

/**
 * <p>
 * 小程序用户关注表 服务类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-12
 */
public interface IAppletFollowService extends IService<AppletFollow> {

    void follow(AppletFollowParam param);
}
