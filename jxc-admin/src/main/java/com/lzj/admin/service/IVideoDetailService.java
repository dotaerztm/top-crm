package com.lzj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.entity.ImageDetail;
import com.lzj.admin.entity.VideoDetail;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageParam;


public interface IVideoDetailService extends IService<VideoDetail> {


    /**
     * 保存视频信息至 数据库
     * @param param
     */
    void saveVideoDetail(ImageParam param);

    /**
     * 删除视频详情 根据标题
     * @param fileName
     * @param fileType
     */
    void delVideoDetailByParentName(String fileName,Integer fileType);

    /**
     * 修改视频详情 根据标题
     * @param oldName
     * @param newName
     * @param fileType
     */
    void updateVideoDetailParentName(String oldName,String newName, Integer fileType);

    /**
     * 视频详情  分页查询
     * @param param
     * @return
     */
    RespBean selectVideoDetailByPage(ImageParam param);

    /**
     * 视频详情  根据主键查询
     * @param id
     * @return
     */
    //RespBean selectImageDetailById(Integer id);


    /**
     * 视频详情 删除
     * @param id
     */
    void delVideoDetail(Integer id);

    /**
     * 视频详情 修改
     * @param param
     */
    void updateVideoDetail(ImageParam param);

}
