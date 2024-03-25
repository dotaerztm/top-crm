package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.entity.AppletFollow;
import com.lzj.admin.entity.AppletMessage;
import com.lzj.admin.entity.AppletUser;
import com.lzj.admin.enums.AppletMessageEnum;
import com.lzj.admin.enums.FollowEnum;
import com.lzj.admin.mapper.AppletMessageMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletFollowPO;
import com.lzj.admin.po.AppletFollowParam;
import com.lzj.admin.po.AppletMessageParam;
import com.lzj.admin.service.IAppletMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 小程序信息通知表 服务实现类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-22
 */
@Service
public class AppletMessageServiceImpl extends ServiceImpl<AppletMessageMapper, AppletMessage> implements IAppletMessageService {

    @Autowired
    AppletUserServiceImpl appletUserServiceImpl;
    @Override
    public void saveMessage(AppletMessageParam param) {
        AppletMessage entity = new AppletMessage();
        BeanUtils.copyProperties(param, entity);
        String contentStr = "";
        if(param.getMessageType() == AppletMessageEnum.FANS.getType()){
            //粉丝记录
            contentStr = "关注了你";
        }else if(param.getMessageType() == AppletMessageEnum.SODA_OBTAIN.getType()){
            //收到汽水
            contentStr = "赠送了你一瓶汽水";
        }else if(param.getMessageType() == AppletMessageEnum.SODA_GIFT.getType()){
            //赠送汽水
            contentStr = "你赠送了你一瓶汽水";
        }else if(param.getMessageType() == AppletMessageEnum.SYSTEM.getType()){
            //系统通知
            contentStr = param.getContent();
        }
        entity.setContent(contentStr);
        entity.setInsertTime(new Date());
        this.save(entity);
    }


    @Override
    public RespBean selectMessageByPage(AppletMessageParam param) {
        Page<AppletMessage> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<AppletMessage> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("message_type",param.getMessageType())
                .eq("uuid",param.getUuid());
        IPage<AppletMessage> pageList = this.page(page, queryWrapper);
        Map<String, Object> returnMap = new HashMap<>();
        List<AppletMessage> list = new ArrayList<>();

        if(!CollectionUtils.isEmpty(pageList.getRecords())){
            List<String> targetUuidStr = pageList.getRecords().stream().map(AppletMessage :: getTargetUuid).collect(Collectors.toList());
            //查询粉丝List用户信息
            QueryWrapper<AppletUser> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.in("uuid",targetUuidStr);
            List<AppletUser> targetList = appletUserServiceImpl.getBaseMapper().selectList(userQueryWrapper);
            System.out.println("targetList="+targetList);
            list = pageList.getRecords().stream()
                    .map(map -> targetList.stream()
                            .filter(x -> Objects.equals(x.getUuid(), map.getTargetUuid()))
                            .findFirst().map(x -> {
                                AppletMessage entity = new AppletMessage();
                                BeanUtils.copyProperties(map, entity);
                                StringBuffer contentStr = new StringBuffer();
                                if(param.getMessageType() == AppletMessageEnum.FANS.getType()
                                        ||param.getMessageType() == AppletMessageEnum.SODA_OBTAIN.getType()){
                                    //粉丝记录,收到汽水
                                    contentStr.append(x.getNickName()).append(map.getContent());
                                }else if(param.getMessageType() == AppletMessageEnum.SODA_GIFT.getType()
                                || param.getMessageType() == AppletMessageEnum.SYSTEM.getType()){
                                    //赠送汽水,系统推送
                                    contentStr.append(map.getContent());
                                }
                                entity.setContent(contentStr.toString());
                                entity.setImageUrl(x.getHeadImgUrl());
                                return entity;
                            }).orElse(null))
                    .filter(Objects::nonNull).collect(Collectors.toList());
        }
        returnMap.put("list",list);
        returnMap.put("count",pageList.getRecords().size());
        return RespBean.success("成功",returnMap);
    }

    public void updateMessageIsRead(Integer id){
        AppletMessage entity = this.baseMapper.selectById(id);
        AssertUtil.isTrue(null == entity,"没有该消息!");
        entity.setIsRead(1);
        entity.setUpdateTime(new Date());
        this.updateById(entity);
    }

    public RespBean selectAllMessageCount(String uuid) {

        QueryWrapper<AppletMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid",uuid)
                .eq("is_read",0);
        List<AppletMessage> list = this.baseMapper.selectList(queryWrapper);
        Map<Integer,Long> map = new HashMap<>();
        if(!CollectionUtils.isEmpty(list)){
            map = list.stream().
                    collect(Collectors.groupingBy(AppletMessage::getMessageType,Collectors.counting()));
        }
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("uuid",uuid);
        returnMap.put("list",map);
        return RespBean.success("成功",returnMap);
    }

}
