package com.lzj.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.entity.ImageAndVideo;
import com.lzj.admin.entity.ImageDetail;
import com.lzj.admin.entity.VideoDetail;
import com.lzj.admin.enums.TypeFlagEnum;
import com.lzj.admin.mapper.ImageAndVideoMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageAndVideoParam;
import com.lzj.admin.service.IMageAndVideoService;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 图片表 服务实现类
 *
 */
@Service
public class ImageAndVideoServiceImpl extends ServiceImpl<ImageAndVideoMapper, ImageAndVideo> implements IMageAndVideoService {

    @Autowired
    ImageDetailServiceImpl imageDetailService;

    @Autowired
    VideoDetailServiceImpl videoDetailService;


    /**
     * 分页查询 图片集合
     * @param param
     * @return
     */
    @Override
    public RespBean selectImageAndVideoByPage(ImageAndVideoParam param) {
        Page<ImageAndVideo> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<ImageAndVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_type",param.getType()).eq("is_del",0)
                .orderByAsc("sort");
        IPage<ImageAndVideo> pageList = this.page(page, queryWrapper);

        Integer count = this.count(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list",pageList.getRecords());
        map.put("count",count);
        return RespBean.success("成功",map);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveImageAndVideoCheckName(ImageAndVideoParam param){
        //验证该图片名称是否存在
        ImageAndVideo entity = this.baseMapper.selectOne(new QueryWrapper<ImageAndVideo>()
                .eq("is_del",0)
                .eq("file_name",param.getName())
                .eq("file_type", param.getType())
                .eq("type_flag", param.getTypeFlag()));
        AssertUtil.isTrue(null != entity,"该名称已存在!");

        ImageAndVideo entiy = this.paramToEntity(param);
        entiy.setInsertTime(new Date());
        AssertUtil.isTrue(!(this.save(entiy)),"保存失败!");

        //判断图片还是视频 添加至对应详情表
        param.setSort(1);//首图默认为1
        if(param.getTypeFlag() == TypeFlagEnum.image.getType()){
            //添加图片详情
            //为了改成之前逻辑  作品新增图片详情  用url字段
            param.setPath(param.getDetailPath());
            //param.setDetailPath(null);
            imageDetailService.saveImageDetail(param);
        }else{
            //添加视频详情
            videoDetailService.saveVideoDetail(param);
        }

    }

    /**
     * param 转 entity
     * @param param
     * @return
     */
    private ImageAndVideo paramToEntity(ImageAndVideoParam param){
        ImageAndVideo entity = new ImageAndVideo();
        entity.setId(param.getId());
        entity.setUrl(param.getPath());
        entity.setFileType(param.getType());
        entity.setFileName(param.getName());
        entity.setRemarks(param.getRemarks());
        entity.setLink(param.getLink());
        entity.setFileGroup(param.getGroup());
        entity.setTypeFlag(param.getTypeFlag());
        entity.setSort(param.getSort());
        entity.setAuthor(param.getAuthor());
        entity.setAuthorImage(param.getAuthorImage());
        entity.setFileTime(param.getTime());
        return entity;
    }

    /**
     * 修改 图片和视频
     * @param param
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateMain(ImageAndVideoParam param){
        ImageAndVideo entity = this.baseMapper.selectOne(new QueryWrapper<ImageAndVideo>().eq("is_del",0)
                .eq("id",param.getId()));
        AssertUtil.isTrue(null == entity,"无该信息!");

        //验证该图片名称是否存在
        ImageAndVideo checkEntity = this.baseMapper.selectOne(new QueryWrapper<ImageAndVideo>()
                .eq("is_del",0)
                .eq("file_name",param.getName())
                .eq("file_type", entity.getFileType())
                .eq("type_flag", entity.getTypeFlag())
                .ne("id", entity.getId())
        );
        AssertUtil.isTrue(null != checkEntity,"该名称已存在!");

        ImageAndVideo image = this.paramToEntity(param);
        image.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(image)),"修改失败!");
        //如果入参标题和库中标题不一致 修改明细中的标题
        if(!entity.getFileName().equals(param.getName())){
            if(entity.getTypeFlag().equals(TypeFlagEnum.image.getType())){
                //修改图片详情标题
                imageDetailService.updateImageDetailParentName(entity.getFileName(),param.getName(),entity.getFileType());
            }else{
                //修改视频详情标题
                videoDetailService.updateVideoDetailParentName(entity.getFileName(),param.getName(),entity.getFileType());
            }
        }

    }


    /**
     * 删除 主作品
     * @param id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void delMain(Integer id) {

        ImageAndVideo entity = this.baseMapper.selectOne(new QueryWrapper<ImageAndVideo>().eq("is_del",0)
                .eq("id",id));
        AssertUtil.isTrue(null == entity,"无该信息!");
        ImageAndVideo mainEntity = new ImageAndVideo();
        mainEntity.setId(id);
        mainEntity.setIsDel(1);
        mainEntity.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(mainEntity)),"删除失败!");

        if(entity.getTypeFlag().equals(TypeFlagEnum.image.getType())){
            //删除图片详情
            imageDetailService.delImageDetailByParentName(entity.getFileName(),entity.getFileType());
        }else{
            videoDetailService.delVideoDetailByParentName(entity.getFileName(),entity.getFileType());
        }
    }


    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @Override
    public RespBean selectImageAndVideoDetailByById(Integer id) {
        ImageAndVideo entity = this.baseMapper.selectOne(new QueryWrapper<ImageAndVideo>().eq("is_del",0)
                .eq("id",id));
        AssertUtil.isTrue(null == entity,"无该信息!");

        Map<String, Object> map = new HashMap<>();
        if(entity.getTypeFlag().equals(TypeFlagEnum.image.getType())){
            List<ImageDetail> detailList = imageDetailService.selectImageDetailListByName(
                    entity.getFileName(),entity.getFileType());
            map.put("list",detailList);
        }else{
            List<VideoDetail>  detailList = videoDetailService.selectVideoDetailListByName(
                    entity.getFileName(),entity.getFileType());
            map.put("list",detailList);
        }
        return RespBean.success("成功",map);
    }

    /**
     * 改变作品是否为 多图状态
     * @param name
     * @param type
     */
    public void updateIsMore(String name , Integer type ,Integer status){
        ImageAndVideo entity = this.baseMapper.selectOne(new QueryWrapper<ImageAndVideo>()
                .eq("is_del",0)
                .eq("file_type",type)
                .eq("file_name",name)
        );
        AssertUtil.isTrue(null == entity,"无该信息!");

        ImageAndVideo updateEntity = new ImageAndVideo();
        updateEntity.setId(entity.getId());
        updateEntity.setIsMore(status);
        updateEntity.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(updateEntity)),"多图状态失败!");
    }
}
