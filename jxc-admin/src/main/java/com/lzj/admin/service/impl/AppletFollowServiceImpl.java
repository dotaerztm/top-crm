package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzj.admin.entity.AppletFans;
import com.lzj.admin.entity.AppletFollow;
import com.lzj.admin.entity.Image;
import com.lzj.admin.enums.FollowEnum;
import com.lzj.admin.mapper.AppletFollowMapper;
import com.lzj.admin.po.AppletFollowParam;
import com.lzj.admin.service.IAppletFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 小程序用户关注表 服务实现类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-12
 */
@Service
public class AppletFollowServiceImpl extends ServiceImpl<AppletFollowMapper, AppletFollow> implements IAppletFollowService {

    @Autowired
    AppletFansServiceImpl appletFansService;

    
    @Override
    public void follow(AppletFollowParam param) {
        //查询是否为首次关注
        AppletFollow entity = this.baseMapper.selectOne(new QueryWrapper<AppletFollow>()
                .eq("uuid",param.getUuid())
                .eq("follow_uuid", param.getFollowUuid()));
        //查询对方是否关注了我
        AppletFollow fansEntity = this.baseMapper.selectOne(new QueryWrapper<AppletFollow>()
                .eq("uuid",param.getFollowUuid())
                .eq("follow_uuid", param.getUuid()));

        //首次关注 增加关注记录
        if(null == entity){
            BeanUtils.copyProperties(param, entity);
            entity.setInsertTime(new Date());
            //对方没有关注我 置为 已关注
            if(null == fansEntity || fansEntity.getFollowStatus() != FollowEnum.FOLLOW.getType()){
                entity.setFollowStatus(FollowEnum.FOLLOWED.getType());
            }else{
                //对方关注了我  置为 互相关注
                entity.setFollowStatus(FollowEnum.MUTUAL.getType());
            }
            AssertUtil.isTrue(!(this.save(entity)),"保存失败!");
        }else{
            //非首次关注 改变关注状态


        }
    }
}
