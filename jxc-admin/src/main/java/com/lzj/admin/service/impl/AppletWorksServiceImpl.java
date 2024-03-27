package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.entity.*;
import com.lzj.admin.enums.AppletUserEnum;
import com.lzj.admin.mapper.AppletWorksMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletFollowPO;
import com.lzj.admin.po.AppletIndexParam;
import com.lzj.admin.po.AppletWorksPO;
import com.lzj.admin.po.AppletWorksParam;
import com.lzj.admin.service.IAppletWorksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

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



    @Autowired
    AppletUserServiceImpl appletUserServiceImpl;



    @Override
    public RespBean selectBestWorksByPage(AppletIndexParam param) {
        Page<AppletWorks> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<AppletWorks> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("works_type",1)//官方推荐
                .eq("works_status",1)//已审核
                .orderByAsc("sort")
                .orderByDesc("insert_time");
        IPage<AppletWorks> pageList = this.page(page, queryWrapper);

        Map<String, Object> returnMap = new HashMap<>();
        List<AppletWorksPO> list = new ArrayList<>();

        if(!CollectionUtils.isEmpty(pageList.getRecords())){
            List<String> uuids = pageList.getRecords().stream().map(AppletWorks :: getUuid).collect(Collectors.toList());
            //查询粉丝List用户信息
            QueryWrapper<AppletUser> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.in("uuid",uuids);
            List<AppletUser> followList = appletUserServiceImpl.getBaseMapper().selectList(userQueryWrapper);
            System.out.println("followLis="+followList);
            list = pageList.getRecords().stream()
                    .map(map -> followList.stream()
                            .filter(x -> Objects.equals(x.getUuid(), map.getUuid()))
                            .findFirst().map(x -> {
                                AppletWorksPO entity = new AppletWorksPO();
                                BeanUtils.copyProperties(x, entity);
                                BeanUtils.copyProperties(map, entity);
                                return entity;
                            }).orElse(null))
                    .filter(Objects::nonNull).collect(Collectors.toList());
            //作品详情 加入集合中
            List<Integer> worksIds = pageList.getRecords().stream().map(AppletWorks :: getId).collect(Collectors.toList());
            QueryWrapper<AppletWorksImage> imageQueryWrapper = new QueryWrapper<>();
            imageQueryWrapper.in("works_id",worksIds);
            List<AppletWorksImage> imageDetailList = appletWorksImageServiceImpl.getBaseMapper().selectList(imageQueryWrapper);
            Map<Integer, List<AppletWorksImage>> imageDetailMap = imageDetailList.stream().collect(Collectors.groupingBy(AppletWorksImage::getWorksId));
            for (AppletWorksPO PO : list) {
                if (imageDetailMap.containsKey(PO.getId())) {
                    PO.setWorksImageList(imageDetailMap.get(PO.getId()));
                }
            }
        }
        returnMap.put("list",list);
        returnMap.put("count",pageList.getRecords().size());
        return RespBean.success("成功",returnMap);
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

    @Override
    public void delWorks(AppletWorksParam param) {
        AppletWorks appletWorks = this.baseMapper.selectById(param.getId());
        AssertUtil.isTrue( null == appletWorks,"没有该作品信息!");
        AssertUtil.isTrue( !param.getUuid().equals(appletWorks.getUuid()),"只有作者可以删除作品!");

        AppletWorks entity = new AppletWorks();
        entity.setId(param.getId());
        entity.setWorksStatus(3);//修改为已删除
        entity.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(entity)),"保存失败!");

    }



    @Override
    public RespBean selectWorksById(AppletWorksParam param) {
        AppletWorks entity = this.baseMapper.selectById(param.getId());
        AssertUtil.isTrue( null == entity,"没有该作品信息!");

        AppletUser userEntity = appletUserServiceImpl.getBaseMapper().selectOne(
                new QueryWrapper<AppletUser>().eq("uuid",entity.getUuid()));
        AssertUtil.isTrue( null == userEntity,"没有该作者信息!");

       // List<AppletWorksImage> worksImageList =

        AppletWorksPO PO = new AppletWorksPO();

        BeanUtils.copyProperties(userEntity, PO);
        BeanUtils.copyProperties(entity, PO);

        //判断是否自己作品
        if(entity.getUuid().equals(param.getUuid())){
            PO.setIsMe(1);
        }else{
            PO.setIsMe(0);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("works",PO);

        return RespBean.success("成功",map);
    }
}
