package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzj.admin.entity.AppletFans;
import com.lzj.admin.entity.AppletFollow;
import com.lzj.admin.enums.FansEnum;
import com.lzj.admin.enums.FollowEnum;
import com.lzj.admin.mapper.AppletFansMapper;
import com.lzj.admin.po.AppletFollowParam;
import com.lzj.admin.service.IAppletFansService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    /**
     * 新增粉丝状态
     * @param uuid
     * @param fansUuid
     * @param fansStatus
     */
    public void addFans(String uuid,String fansUuid,Integer fansStatus){
        AppletFans entity = this.baseMapper.selectOne(new QueryWrapper<AppletFans>()
                .eq("uuid",uuid)
                .eq("fans_uuid", fansUuid));
        Integer status = fansStatus == FollowEnum.MUTUAL.getType() ? FansEnum.MUTUAL.getType() :FansEnum.BACK_FOLLOWED.getType();
        //没有粉丝记录 新增记录
        if(null == entity){
            AppletFans addEntity = new AppletFans();
            addEntity.setUuid(uuid);
            addEntity.setFansUuid(fansUuid);
            addEntity.setFansStatus(status);
            addEntity.setInsertTime(new Date());
            AssertUtil.isTrue(!(this.save(addEntity)),"保存失败!");
        }else{
            entity.setFansStatus(status);
            entity.setUpdateTime(new Date());
            AssertUtil.isTrue(!(this.updateById(entity)),"保存失败!");
        }
    }
}

