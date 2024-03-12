package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzj.admin.entity.AppletFans;
import com.lzj.admin.entity.AppletFollow;
import com.lzj.admin.mapper.AppletFansMapper;
import com.lzj.admin.po.AppletFollowParam;
import com.lzj.admin.service.IAppletFansService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小程序用户粉丝表 服务实现类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-12
 */
@Service
public class AppletFansServiceImpl extends ServiceImpl<AppletFansMapper, AppletFans> implements IAppletFansService {

    public AppletFans selectFans(AppletFollowParam param){
        //查询粉丝状态
        AppletFans entity = this.baseMapper.selectOne(new QueryWrapper<AppletFans>()
                .eq("uuid",param.getFollowUuid())
                .eq("fans_uuid", param.getUuid()));
        return entity;
    }
}
