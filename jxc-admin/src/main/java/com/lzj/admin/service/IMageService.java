package com.lzj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.entity.Image;
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
public interface IMageService extends IService<Image> {

    /**
     * File文件保存到本地/服务器
     * @param file
     * @param filePath
     * @return
     * @throws IOException
     */
    String saveFileToLocal(MultipartFile file , String filePath) throws IOException;

    /**
     * 保存图片信息至 数据库
     * @param param
     */
    void saveImageToDataBase(ImageParam param);

    /**
     * 保存图片信息至 数据库 验证图片名称
     * @param param
     */
    void saveImageToDataBaseCheckName(ImageParam param);

    /**
     * 修改图片信息
     * @param param
     */
    void updateImage(ImageParam param);

    /**
     * 修改 主图片
     * @param param
     */
    void updateImageMain(ImageParam param);

    /**
     * 删除图片信息
     * @param id
     */
    void delImage(Integer id);

    /**
     * 删除 主图图片
     * @param id
     */
    void delImageMain(Integer id);

    /**
     * 图片分页查询
     * @param param
     * @return
     */
    RespBean selectImageByPage(ImageParam param);

    /**
     * 查询图片详情 根据主键
     * @param id
     * @return
     */
    RespBean selectImageDetailById(Integer id);


    /**
     * 查看图片详情  根据名称分组
     * @param id
     * @return
     */
    RespBean selectImageDetailGroupName(Integer id);





    String saveThumbnailToLocal(String imageUrl);
}
