package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.entity.Image;
import com.lzj.admin.entity.ImageDetail;
import com.lzj.admin.entity.VideoDetail;
import com.lzj.admin.mapper.VideoMapper;
import com.lzj.admin.entity.Video;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageParam;
import com.lzj.admin.po.VideoParam;
import com.lzj.admin.service.IVideoService;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片表 服务实现类
 *
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

    @Autowired
    VideoDetailServiceImpl videoDetailServiceImpl;

    /**
     * 保存视频信息到数据库
     * @param param
     */
    @Override
    public void saveVideoToDataBase(VideoParam param) {
        Video video = this.paramToEntity(param);
        video.setInsertTime(new Date());
        AssertUtil.isTrue(!(this.save(video)),"视频上传失败!");

    }

    /**
     * 修改 视频信息
     * @param param
     */
    @Override
    public void updateVideo(VideoParam param) {
        Video entity = this.baseMapper.selectOne(new QueryWrapper<Video>().eq("is_del",0)
                .eq("id",param.getId()));
        AssertUtil.isTrue(null == entity,"无该视频信息!");
        Video video = this.paramToEntity(param);
        video.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(video)),"视频修改失败!");
    }

    /**
     * param 转 entity
     * @param param
     * @return
     */
    private Video paramToEntity(VideoParam param){
        Video video = new Video();
        video.setId(param.getId());
        video.setUrl(param.getPath());
        video.setFileType(param.getType());
        video.setFileName(param.getName());
        video.setRemarks(param.getRemarks());
        video.setLink(param.getLink());
        video.setFileGroup(param.getGroup());
        video.setJob(param.getJob());
        video.setSort(param.getSort());
        video.setAuthor(param.getAuthor());
        video.setFileTime(param.getTime());
        return video;
    }

    /**
     * 分页查询 视频集合
     * @param param
     * @return
     */
    @Override
    public RespBean selectVideoByPage(VideoParam param) {
        Page<Video> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_type",param.getType())
                .eq("is_del",0)
                .orderByAsc("sort");
        IPage<Video> pageList = this.page(page, queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list",pageList.getRecords());
        map.put("count",pageList.getRecords().size());
        return RespBean.success("成功",map);
    }


    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @Override
    public RespBean selectVideoDetailById(Integer id) {
        Video entity = this.baseMapper.selectOne(new QueryWrapper<Video>().eq("is_del",0)
                .eq("id",id));
        AssertUtil.isTrue(null == entity,"无该信息!");
        List<VideoDetail> detailList = videoDetailServiceImpl.selectVideoDetailListByName(
                entity.getFileName(),entity.getFileType());
        Map<String, Object> map = new HashMap<>();
        map.put("list",detailList);
        return RespBean.success("成功",map);
    }

    /**
     * 删除主视频
     * @param id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void delVideoMain(Integer id) {

        Video entity = this.baseMapper.selectOne(new QueryWrapper<Video>().eq("is_del", 0)
                .eq("id", id));
        AssertUtil.isTrue(null == entity, "无该信息!");
        Video video = new Video();
        video.setId(id);
        video.setIsDel(1);
        video.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(video)), "删除失败!");

        //删除图片详情
        videoDetailServiceImpl.delVideoDetailByParentName(entity.getFileName(), entity.getFileType());
    }


}
