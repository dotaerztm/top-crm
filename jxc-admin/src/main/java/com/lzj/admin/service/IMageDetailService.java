package com.lzj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.entity.Image;
import com.lzj.admin.entity.ImageDetail;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 老李
 */
public interface IMageDetailService extends IService<ImageDetail> {


    /**
     * 保存图片信息至 数据库
     * @param param
     */
    void saveImageDetail(ImageParam param);

    /**
     * 图片详情 保存
     * 该方法无需验证 图片详情唯一性
     */
    void saveDetailByGrade(ImageParam param);

    /**
     * 删除图片详情 根据标题
     * @param fileName
     * @param fileType
     */
    void delImageDetailByParentName(String fileName,Integer fileType);

    /**
     * 修改图片详情 根据标题
     * @param oldName
     * @param newName
     * @param fileType
     */
    void updateImageDetailParentName(String oldName,String newName, Integer fileType);

    /**
     * 图片详情  分页查询
     * @param param
     * @return
     */
    RespBean selectImageDetailByPage(ImageParam param);

    /**
     * 图片详情  根据主键查询
     * @param id
     * @return
     */
    //RespBean selectImageDetailById(Integer id);


    /**
     * 图片详情 删除
     * @param id
     */
    void delImageDetail(Integer id);

    /**
     * 图片详情 修改
     * @param param
     */
    void updateImageDetail(ImageParam param);

}
