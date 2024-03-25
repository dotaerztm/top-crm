package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzj.admin.entity.AppletPoint;
import com.lzj.admin.entity.AppletUser;
import com.lzj.admin.enums.PointStatusEnum;
import com.lzj.admin.mapper.AppletPointMapper;
import com.lzj.admin.service.IAppletPointService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 小程序-用户积分表 服务实现类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
@Service
public class AppletPointServiceImpl extends ServiceImpl<AppletPointMapper, AppletPoint> implements IAppletPointService {
    /**
     * 用户注册成功 初始化一条积分记录
     * @param uuid
     */
    @Override
    public void initUserPoint(String uuid) {
        AppletPoint pointEntity = this.getOne(new QueryWrapper<AppletPoint>()
                .eq("uuid", uuid));
        if(null == pointEntity){
            AppletPoint entity =  new  AppletPoint();
            entity.setUuid(uuid);
            entity.setPointCurrent(BigDecimal.ZERO);
            entity.setPointSum(BigDecimal.ZERO);
            this.save(entity);
        }
        //注册成功 增加 每日小红花记录

    }

    public void updatePointByUuid(String uuid ,BigDecimal point ,Integer status){
        AppletPoint pointEntity = this.getOne(new QueryWrapper<AppletPoint>()
                .eq("uuid", uuid));
        AssertUtil.isTrue(null == pointEntity,"用户积分数据错误!");
        if(status == PointStatusEnum.ADD.getType()){
            pointEntity.setPointSum(pointEntity.getPointSum().add(point));
            pointEntity.setPointCurrent(pointEntity.getPointCurrent().add(point));
        }else{
            BigDecimal currentPoint =pointEntity.getPointCurrent().subtract(point);
            AssertUtil.isTrue(currentPoint.compareTo(BigDecimal.ZERO) == -1,"用户积分不足!");
            pointEntity.setPointCurrent(currentPoint);
        }
        pointEntity.setUpdateTime(new Date());
        this.updateById(pointEntity);
    }
}
