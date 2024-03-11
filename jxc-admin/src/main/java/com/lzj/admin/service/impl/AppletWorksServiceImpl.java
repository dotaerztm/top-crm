package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.entity.AppletWorks;
import com.lzj.admin.entity.AppletWorksImage;
import com.lzj.admin.entity.Video;
import com.lzj.admin.mapper.AppletWorksMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletIndexParam;
import com.lzj.admin.po.AppletWorksParam;
import com.lzj.admin.service.IAppletWorksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小程序作品表 服务实现类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
@Service
public class AppletWorksServiceImpl extends ServiceImpl<AppletWorksMapper, AppletWorks> implements IAppletWorksService {

    @Autowired
    AppletWorksImageServiceImpl appletWorksImageServiceImpl;


    @Override
    public RespBean selectWorksByPage(AppletIndexParam param) {
        Page<AppletWorks> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<AppletWorks> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("works_type",param.getWorksType())
                .eq("works_status",1)
                .orderByAsc("sort");
        IPage<AppletWorks> pageList = this.page(page, queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list",pageList.getRecords());
        map.put("count",pageList.getRecords().size());
        return RespBean.success("成功",map);
    }


    @Override
    @Transactional
    public void saveWorks(AppletWorksParam param) {
        AppletWorks entity = new AppletWorks();
        entity.setInsertTime(new Date());
        BeanUtils.copyProperties(param, entity);

        //新增作品主体信息
        AssertUtil.isTrue(!(this.save(entity)),"保存失败!");

        if(!CollectionUtils.isEmpty(param.getWorksImageList())){
            //新增作品图片详情
            List<AppletWorksImage> list = param.getWorksImageList();
            list.forEach( p -> p.setWorksId(entity.getId()));
            list.forEach( p -> p.setUuid(entity.getUuid()));
            list.forEach( p -> p.setInsertTime(new Date()));
            appletWorksImageServiceImpl.addWorkImageList(param.getWorksImageList());
        }
    }


    @Override
    @Transactional
    public void updateWorks(AppletWorksParam param) {
        AppletWorks entity = new AppletWorks();
        entity.setUpdateTime(new Date());
        BeanUtils.copyProperties(param, entity);

        //修改作品主体信息
        AssertUtil.isTrue(!(this.updateById(entity)),"保存失败!");

        if(!CollectionUtils.isEmpty(param.getWorksImageList())){
            //修改作品图片详情
            List<AppletWorksImage> list = param.getWorksImageList();
            list.forEach( p -> p.setWorksId(entity.getId()));
            list.forEach( p -> p.setUpdateTime(new Date()));
            appletWorksImageServiceImpl.updateWorkImageList(param.getWorksImageList());
        }
    }
}
