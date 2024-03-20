package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzj.admin.entity.AppletFollow;
import com.lzj.admin.entity.AppletWorks;
import com.lzj.admin.entity.AppletWorksLike;
import com.lzj.admin.mapper.AppletWorksLikeMapper;
import com.lzj.admin.po.AppletWorksLikeParam;
import com.lzj.admin.service.IAppletWorksLikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 小程序作品点赞表 服务实现类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
@Service
public class AppletWorksLikeServiceImpl extends ServiceImpl<AppletWorksLikeMapper, AppletWorksLike> implements IAppletWorksLikeService {

    @Autowired
    AppletWorksServiceImpl appletWorksServiceImpl;
    @Override
    @Transactional
    public void worksLike(AppletWorksLikeParam param) {
        //查看用户是否点赞过该作品
        AppletWorksLike likeEntity = this.baseMapper.selectOne(new QueryWrapper<AppletWorksLike>()
                .eq("words_id",param.getWorksId())
                .eq("uuid", param.getUuid()));
        //如果没有 新增点赞记录
        if(null == likeEntity){
            AppletWorksLike addEntity = new AppletWorksLike();
            BeanUtils.copyProperties(param, addEntity);
            addEntity.setInsertTime(new Date());
            this.save(addEntity);
        }else{
            likeEntity.setLikeStatus(param.getLikeStatus());
            likeEntity.setInsertTime(new Date());
            this.updateById(likeEntity);
        }
        //查看该作品信息 修改点赞数
        AppletWorks appletWorks = appletWorksServiceImpl.getBaseMapper().selectById(param.getWorksId());
        appletWorks.setLikeCount(param.getLikeStatus() == 1 ? appletWorks.getLikeCount()+1 :appletWorks.getLikeCount()-1);
        appletWorks.setUpdateTime(new Date());
        appletWorksServiceImpl.updateById(appletWorks);
    }
}
