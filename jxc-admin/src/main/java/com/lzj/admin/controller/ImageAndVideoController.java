package com.lzj.admin.controller;


import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageAndVideoParam;
import com.lzj.admin.po.ImageParam;
import com.lzj.admin.service.IMageAndVideoService;
import com.lzj.admin.service.IMageDetailService;
import com.lzj.admin.service.IMageService;
import com.lzj.admin.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;

/**
 * 作品 前端控制器
 */
@Slf4j
@CrossOrigin
@Controller
@RequestMapping("/imageAndVideo")
public class ImageAndVideoController {


    @Resource
    private IMageAndVideoService imageAndVideoService;

    /**
     * 图片&视频 分页查询
     * @param param
     * @return
     */
    @RequestMapping("/selectImageAndVideoByPage")
    @ResponseBody
    public RespBean selectImageAndVideoByPage(@RequestBody ImageAndVideoParam param) {
        log.info("调用接口【图片&视频分页查询】");
        try {
            if(null == param.getType()){
                return RespBean.error("类型不能为空!");
            }
            RespBean respBean = imageAndVideoService.selectImageAndVideoByPage(param);
            return respBean;
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("查询失败!");
        }
    }

    /**
     * 新增 图片&视频
     * @param param
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public RespBean save(@RequestBody ImageAndVideoParam param) {
        try {
            if (StringUtil.isEmpty(param.getPath())) {
                return RespBean.error("图片信息不能为空!");
            }else if(null == param.getTypeFlag()){
                return RespBean.error("类型标识不能为空!");
            }
            //图片信息保存至 数据库
            imageAndVideoService.saveImageAndVideoCheckName(param);
            return RespBean.success("保存成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("保存失败!");
        }
    }

    /**
     * 修改 主作品
     * @param param
     * @return
     */
    @RequestMapping("/updateMain")
    @ResponseBody
    public RespBean updateMain(@RequestBody ImageAndVideoParam param) {
        try {
            if (null == param.getId()) {
                return RespBean.error("主键不能为空!");
            }
            if(null == param.getName()){
                return RespBean.error("标题不能为空!");
            }
            imageAndVideoService.updateMain(param);
            return RespBean.success("修改成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("修改失败!");
        }
    }

    /**
     * 删除 主作品
     * @param id 主键
     * @return
     */
    @RequestMapping("/delMain")
    @ResponseBody
    public RespBean delMain(@RequestBody Integer id) {
        try {
            if (null == id) {
                return RespBean.error("主键不能为空!");
            }
            imageAndVideoService.delMain(id);
            return RespBean.success("删除成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("删除失败!");
        }
    }


    /**
     * 图片和视频详情 查询
     * @param param
     * @return
     */
    @RequestMapping("/selectImageAndVideoDetailByById")
    @ResponseBody
    public RespBean selectImageAndVideoDetailByById(@RequestBody ImageAndVideoParam param) {
        log.info("调用接口【作品详情分页查询】");
        try {
            if(null == param.getId()){
                return RespBean.error("不能为空!");
            }
            RespBean respBean = imageAndVideoService.selectImageAndVideoDetailByById(param.getId());
            return respBean;
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("查询失败!");
        }
    }

}
