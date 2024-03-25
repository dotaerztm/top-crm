package com.lzj.admin.service;

import com.lzj.admin.entity.AppletPoint;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 小程序-用户积分表 服务类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
public interface IAppletPointService extends IService<AppletPoint> {

    void initUserPoint(String uuid);

    void updatePointByUuid(String uuid , BigDecimal point , Integer status);


}
