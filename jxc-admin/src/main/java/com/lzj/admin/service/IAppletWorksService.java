package com.lzj.admin.service;

import com.lzj.admin.entity.AppletWorks;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletIndexParam;
import com.lzj.admin.po.AppletWorksParam;

/**
 * <p>
 * 小程序作品表 服务类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
public interface IAppletWorksService extends IService<AppletWorks> {

    RespBean selectWorksByPage(AppletIndexParam param);

    void saveWorks(AppletWorksParam param);

    void updateWorks(AppletWorksParam param);
}
