package com.lzj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.entity.ImageAndVideo;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageAndVideoParam;
import com.lzj.admin.po.ImageParam;



public interface IMageAndVideoService extends IService<ImageAndVideo> {

    /**
     * 作品 分页查询
     * @param param
     * @return
     */
    RespBean selectImageAndVideoByPage(ImageAndVideoParam param);

    /**
     * 保存 作品
     * @param param
     */
    void saveImageAndVideoCheckName(ImageAndVideoParam param);

    /**
     * 修改 作品
     * @param param
     */
    void updateMain(ImageAndVideoParam param);

    /**
     * 删除 作品
     * @param id
     */
    void delMain(Integer id);

    /**
     * 图片和视频详情 查询
     */
    RespBean  selectImageAndVideoDetailByById(Integer id);
}
