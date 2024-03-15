package com.lzj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.entity.AppletUser;
import com.lzj.admin.entity.User;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletIndexParam;
import com.lzj.admin.po.AppletUserParam;

/**
 * 小程序-首页-用户 服务类
 *
 * @author 老李
 */
public interface IAppletUserService extends IService<AppletUser> {


    /**
     * 小程序 用户登录
     * @param param
     * @return
     */
    RespBean login(AppletUserParam param);

    /**
     * 小程序 用户注册
     * @param param
     * @return
     */
    RespBean register(AppletUserParam param);

}
