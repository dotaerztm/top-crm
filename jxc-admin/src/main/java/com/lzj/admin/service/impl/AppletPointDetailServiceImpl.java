package com.lzj.admin.service.impl;

import com.lzj.admin.entity.AppletPointDetail;
import com.lzj.admin.mapper.AppletPointDetailMapper;
import com.lzj.admin.service.IAppletPointDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小程序-用户积分详情 服务实现类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
@Service
public class AppletPointDetailServiceImpl extends ServiceImpl<AppletPointDetailMapper, AppletPointDetail> implements IAppletPointDetailService {

    @Autowired
    AppletPointServiceImpl appletPointServiceImpl;
    public void addPointDetail(AppletPointDetail param){
        this.save(param);
        //积分增加完毕 用户积分汇总改变
        appletPointServiceImpl.updatePointByUuid(param.getUuid(),param.getPoint(),param.getPointStatus());
    }


}
