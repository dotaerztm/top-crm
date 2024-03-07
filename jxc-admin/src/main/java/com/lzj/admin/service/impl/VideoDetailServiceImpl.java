package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.entity.ImageDetail;
import com.lzj.admin.entity.VideoDetail;
import com.lzj.admin.mapper.ImageDetailMapper;
import com.lzj.admin.mapper.VideoDetailMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageAndVideoParam;
import com.lzj.admin.po.ImageParam;
import com.lzj.admin.service.IMageDetailService;
import com.lzj.admin.service.IVideoDetailService;
import com.lzj.admin.service.IVideoService;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 视频详情表 服务实现类
 *
 */
@Service
public class VideoDetailServiceImpl extends ServiceImpl<VideoDetailMapper, VideoDetail> implements IVideoDetailService {




    /**
     * 保存 图片详情
     * @param param
     */
    @Override
    public void saveVideoDetail(ImageParam param) {
        VideoDetail detail = this.paramToEntity(param);
        detail.setInsertTime(new Date());
        AssertUtil.isTrue(!(this.save(detail)),"保存失败!");
    }

    /**
     * 保存 图片详情
     * @param param
     */
    public void saveVideoDetail(ImageAndVideoParam param) {
        VideoDetail detail = this.paramToEntity(param);
        detail.setInsertTime(new Date());
        AssertUtil.isTrue(!(this.save(detail)),"保存失败!");
    }




    /**
     * ImageParam 转 entity
     * @param param
     * @return
     */
    private VideoDetail paramToEntity(ImageParam param){
        VideoDetail detail = new VideoDetail();
        detail.setParentName(param.getName());
        detail.setUrl(param.getDetailPath());
        detail.setFileType(param.getType());
        detail.setSort(param.getSort());
        return detail;
    }

    /**
     * ImageAndVideoParam 转 entity
     * @param param
     * @return
     */
    private VideoDetail paramToEntity(ImageAndVideoParam param){
        VideoDetail detail = new VideoDetail();
        detail.setParentName(param.getName());
        detail.setUrl(param.getDetailPath());
        detail.setFileType(param.getType());
        detail.setSort(param.getSort());
        return detail;
    }

    /**
     * 删除图片详情 By fileName & fileType
     * @param fileName
     */
    @Override
    public void delVideoDetailByParentName(String fileName, Integer fileType){
        VideoDetail entity = new  VideoDetail();
        entity.setIsDel(1);
        entity.setUpdateTime(new Date());
        QueryWrapper wrapper = new QueryWrapper<VideoDetail>().eq("is_del",0)
                .eq("parent_name",fileName).eq("file_type",fileType);
        AssertUtil.isTrue(!(this.update(entity,wrapper)),"详情删除失败!");
    }

    /**
     * 图片详情页 修改parentName
     * @param oldName
     * @param newName
     * @param fileType
     */
    @Override
    public void updateVideoDetailParentName(String oldName,String newName, Integer fileType){
        VideoDetail entity = new  VideoDetail();
        entity.setParentName(newName);
        entity.setUpdateTime(new Date());
        QueryWrapper wrapper = new QueryWrapper<VideoDetail>().eq("is_del",0)
                .eq("parent_name",oldName).eq("file_type",fileType);
        AssertUtil.isTrue(!(this.update(entity,wrapper)),"详情删除失败!");
    }

    /**
     * 详情页 分页查询
     * @param param
     * @return
     */
    @Override
    public RespBean selectVideoDetailByPage(ImageParam param) {
        Page<VideoDetail> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<VideoDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_type",param.getType())
                .eq("is_del",0)
                .orderByAsc("sort");
        IPage<VideoDetail> pageList = this.page(page, queryWrapper);

        Integer count = this.count(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list",pageList.getRecords());
        map.put("count",count);
        return RespBean.success("成功",map);
    }





    /**
     * 图片详情 删除
     * @param id
     */
    @Override
    public void delVideoDetail(Integer id){
        VideoDetail entity = this.baseMapper.selectOne(new QueryWrapper<VideoDetail>().eq("is_del",0)
                .eq("id",id));
        AssertUtil.isTrue(null == entity,"无该信息!");

        QueryWrapper<VideoDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_type",entity.getFileType()).eq("parent_name",entity.getParentName())
                .eq("is_del",0);
        AssertUtil.isTrue(this.count(queryWrapper) <= 1,"至少保留一张详情图!");

        VideoDetail detail = new VideoDetail();
        detail.setId(id);
        detail.setIsDel(1);
        detail.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(detail)),"删除失败!");
    }

    /**
     * 图片详情 修改
     * @param param
     */
    @Override
    public void updateVideoDetail(ImageParam param){
        VideoDetail entity = this.baseMapper.selectOne(new QueryWrapper<VideoDetail>().eq("is_del",0)
                .eq("id",param.getId()));
        AssertUtil.isTrue((null == entity),"无该信息!");

        VideoDetail detail = new VideoDetail();
        detail.setId(param.getId());
        detail.setUrl(param.getDetailPath());
        detail.setSort(param.getSort());
        detail.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(detail)),"修改失败!");
    }

    /**
     * 查询图片详情 根据主图标题
     * @param name
     * @param type
     * @return
     */
    public List<VideoDetail> selectVideoDetailListByName(String name,Integer type){
        List<VideoDetail> detailList =  baseMapper.selectList(new QueryWrapper<VideoDetail>().eq("is_del",0)
                .eq("parent_name",name).eq("file_type",type));
        return detailList;
    }




}
