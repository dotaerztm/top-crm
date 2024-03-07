package com.lzj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.entity.Video;
import com.lzj.admin.entity.VideoDetail;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageParam;
import com.lzj.admin.po.VideoParam;


public interface IVideoService extends IService<Video> {


    /**
     * 保存视频信息至 数据库
     * @param param
     */
    void saveVideoToDataBase(VideoParam param);

    /**
     * 修海视频
     * @param param
     */
    void updateVideo(VideoParam param);

    /**
     * 分页查询 图片集合
     * @param param
     * @return
     */
    RespBean selectVideoByPage(VideoParam param);

    /**
     * 查询视频详情 根据主键
     * @param id
     * @return
     */
    RespBean selectVideoDetailById(Integer id);


    /**
     * 删除 主视频
     * @param id
     */
    void delVideoMain(Integer id);


}
