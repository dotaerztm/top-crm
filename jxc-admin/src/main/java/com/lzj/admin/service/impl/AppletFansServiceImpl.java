package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.entity.AppletFans;
import com.lzj.admin.entity.AppletFollow;
import com.lzj.admin.enums.FansEnum;
import com.lzj.admin.enums.FollowEnum;
import com.lzj.admin.mapper.AppletFansMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletFollowParam;
import com.lzj.admin.service.IAppletFansService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        //Integer status = fansStatus == FollowEnum.MUTUAL.getType() ? FansEnum.MUTUAL.getType() :FansEnum.BACK_FOLLOWED.getType();
        //没有粉丝记录 新增记录
        if(null == entity){
            AppletFans addEntity = new AppletFans();
            addEntity.setUuid(uuid);
            addEntity.setFansUuid(fansUuid);
            addEntity.setFansStatus(fansStatus);
            addEntity.setInsertTime(new Date());
            AssertUtil.isTrue(!(this.save(addEntity)),"保存失败!");
        }else{
            entity.setFansStatus(fansStatus);
            entity.setUpdateTime(new Date());
            AssertUtil.isTrue(!(this.updateById(entity)),"保存失败!");
        }

        //修改互为粉丝的记录 粉丝状态
        AppletFans updateEntity = this.baseMapper.selectOne(new QueryWrapper<AppletFans>()
                .eq("fans_uuid",uuid)
                .eq("uuid", fansUuid));

        if(null != updateEntity){
            if(fansStatus == FollowEnum.FOLLOW.getType() && updateEntity.getFansStatus() == FansEnum.MUTUAL.getType()){
                updateEntity.setFansStatus(FansEnum.BACK_FOLLOWED.getType());
                updateEntity.setUpdateTime(new Date());
                AssertUtil.isTrue(!(this.updateById(updateEntity)),"保存失败!");
            }
            if(fansStatus == FollowEnum.MUTUAL.getType()){
                updateEntity.setFansStatus(FansEnum.MUTUAL.getType());
                updateEntity.setUpdateTime(new Date());
                AssertUtil.isTrue(!(this.updateById(updateEntity)),"保存失败!");
            }
        }
    }

    @Override
    public RespBean selectFansByPage(AppletFollowParam param) {
        Page<AppletFans> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<AppletFans> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid",param.getUuid())
                .ne("fans_status",FollowEnum.FOLLOW.getType())
                .orderByDesc("insert_time");
        IPage<AppletFans> pageList = this.page(page, queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list",pageList.getRecords());
        map.put("count",pageList.getRecords().size());
        return RespBean.success("成功",map);
    }
}

