package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.entity.AppletFans;
import com.lzj.admin.entity.AppletFollow;
import com.lzj.admin.entity.AppletWorks;
import com.lzj.admin.entity.Image;
import com.lzj.admin.enums.FollowEnum;
import com.lzj.admin.mapper.AppletFollowMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletFollowParam;
import com.lzj.admin.po.AppletIndexParam;
import com.lzj.admin.service.IAppletFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public RespBean follow(AppletFollowParam param) {
        //查询是否为首次关注
        AppletFollow followEntity = this.baseMapper.selectOne(new QueryWrapper<AppletFollow>()
                .eq("uuid",param.getUuid())
                .eq("follow_uuid", param.getFollowUuid()));
        //查询对方是否关注了我
        AppletFollow fansEntity = this.baseMapper.selectOne(new QueryWrapper<AppletFollow>()
                .eq("uuid",param.getFollowUuid())
                .eq("follow_uuid", param.getUuid()));

        Map<String, Object> map = new HashMap<>();

        //首次关注 增加关注记录
        if(null == followEntity){
            AppletFollow entity = new AppletFollow();
            entity.setInsertTime(new Date());
            BeanUtils.copyProperties(param, entity);
            //对方没有关注我 置为 已关注
            if(null == fansEntity || fansEntity.getFollowStatus() == FollowEnum.FOLLOW.getType()){
                entity.setFollowStatus(FollowEnum.FOLLOWED.getType());
            }else{
                //对方关注了我  置为 互相关注
                entity.setFollowStatus(FollowEnum.MUTUAL.getType());
            }
            AssertUtil.isTrue(!(this.save(entity)),"保存失败!");
            map.put("id",entity.getId());
            map.put("status",entity.getFollowStatus());
        }else{
            //非首次关注 改变关注状态
            followEntity.setUpdateTime(new Date());
            //如果现在是 非关注状态 置为 已关注 或者 互相关注
            if(followEntity.getFollowStatus() == FollowEnum.FOLLOW.getType()){
                //对方没关注我 置为 已关注
                if(null == fansEntity || fansEntity.getFollowStatus() == FollowEnum.FOLLOW.getType()){
                    followEntity.setFollowStatus(FollowEnum.FOLLOWED.getType());
                }else{
                    //对方关注了我 置为 互相关注
                    followEntity.setFollowStatus(FollowEnum.MUTUAL.getType());
                }
            }else{
                //现在是关注状态 置为 关注
                followEntity.setFollowStatus(FollowEnum.FOLLOW.getType());
            }
            AssertUtil.isTrue(!(this.updateById(followEntity)),"保存失败!");
            map.put("id",followEntity.getId());
            map.put("status",followEntity.getFollowStatus());
        }
        if(null != fansEntity){
            updateFansStatus((Integer) map.get("status"),fansEntity);
        }
        //新增 粉丝表记录
        appletFansService.addFans(param.getFollowUuid(),param.getUuid(),(Integer) map.get("status"));
        return RespBean.success("成功",map);
    }

    /**
     * 修改关注表 被关注者状态
     * @param followStatus
     * @param fansEntity
     */
    public void updateFansStatus(Integer followStatus ,AppletFollow fansEntity){
        fansEntity.setUpdateTime(new Date());
        //如果关注者状态为 互相关注 被关注者也修改为 互相关注
        if(followStatus == FollowEnum.MUTUAL.getType()){
            fansEntity.setFollowStatus(FollowEnum.MUTUAL.getType());
            AssertUtil.isTrue(!(this.updateById(fansEntity)),"保存失败!");
        }else if(followStatus == FollowEnum.FOLLOW.getType() && fansEntity.getFollowStatus() == FollowEnum.MUTUAL.getType()){
            //如果关注者状态为 取消关注 被关注者修改为 已关注
            fansEntity.setFollowStatus(FollowEnum.FOLLOWED.getType());
            AssertUtil.isTrue(!(this.updateById(fansEntity)),"保存失败!");
        }
    }


    @Override
    public RespBean selectFollowByPage(AppletFollowParam param) {
        Page<AppletFollow> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<AppletFollow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid",param.getUuid())
                .ne("follow_status",FollowEnum.FOLLOW.getType())
                .orderByDesc("insert_time");
        IPage<AppletFollow> pageList = this.page(page, queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list",pageList.getRecords());
        map.put("count",pageList.getRecords().size());
        return RespBean.success("成功",map);
    }
}
