package com.lzj.admin.controller;


import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageParam;
import com.lzj.admin.service.IMageDetailService;
import com.lzj.admin.service.IMageService;
import com.lzj.admin.utils.StringUtil;
import com.lzj.admin.utils.UploadAliOSS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;

/**
 * 图片表 前端控制器
 */
@Slf4j
@CrossOrigin
@Controller
@RequestMapping("/image")
public class ImageController {

    /**
     * 上传图片
     */
    @Resource
    private IMageService imageService;

    @Resource
    private IMageDetailService imageDetailService;

    @RequestMapping("/upload")
    @ResponseBody
    public RespBean uploadImage(MultipartFile file) throws Exception {
        System.out.println("调用接口->>【上传图片】");
        if (null == file.getOriginalFilename()) {
            return RespBean.error("图片信息不能为空!");
        }
        if (!file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg")) {
            return RespBean.error("非图片类型文件!");
        }
        String url = UploadAliOSS.uploadFile(file);
        HashMap<String,Object> map = new HashMap<>();
        map.put("src",url);
        return RespBean.success("图片上传成功!",map);
    }

    /**
     * 保存图片
     */
    @RequestMapping("/save")
    @ResponseBody
    public RespBean saveImage(@RequestBody ImageParam param) {
        try {
            if (StringUtil.isEmpty(param.getPath())) {
                return RespBean.error("图片信息不能为空!");
            }
            //图片信息保存至 数据库
            imageService.saveImageToDataBase(param);
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
     * 保存图片-需要验证图片名称
     */
    @RequestMapping("/saveCheckName")
    @ResponseBody
    public RespBean saveImageCheckName(@RequestBody ImageParam param) {
        try {
            if (StringUtil.isEmpty(param.getPath()) && StringUtil.isEmpty(param.getDetailPath())) {
                return RespBean.error("图片信息不能为空!");
            }
            //图片信息保存至 数据库
            imageService.saveImageToDataBaseCheckName(param);
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
     * 修改图片
     * @param param
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public RespBean updateImage(@RequestBody ImageParam param) {
        try {
            if (null == param.getId()) {
                return RespBean.error("主键不能为空!");
            }
            imageService.updateImage(param);
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
     * 删除图片
     * @param id 主键
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public RespBean delImage(@RequestBody Integer id) {
        try {
            if (null == id) {
                return RespBean.error("主键不能为空!");
            }
            imageService.delImage(id);
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
     * 图片分页查询
     * @param param
     * @return
     */
    @RequestMapping("/selectImageByPage")
    @ResponseBody
    public RespBean selectImageByPage(@RequestBody ImageParam param) {
        log.info("调用接口【图片分页查询】");
        try {
            if(null == param.getType()){
                return RespBean.error("类型不能为空!");
            }
            RespBean respBean = imageService.selectImageByPage(param);
            System.out.println("respBean=="+respBean);
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
     * 图片详情 分页查询
     * @param param
     * @return
     */
    @RequestMapping("/selectImageDetailByPage")
    @ResponseBody
    public RespBean selectImageDetailByPage(@RequestBody ImageParam param) {
        log.info("调用接口【图片详情分页查询】");
        try {
            if(null == param.getType()){
                return RespBean.error("类型不能为空!");
            }
            RespBean respBean = imageDetailService.selectImageDetailByPage(param);
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
     * 图片详情 查询
     * @param param
     * @return
     */
    @RequestMapping("/selectImageDetailById")
    @ResponseBody
    public RespBean selectImageDetailById(@RequestBody ImageParam param) {
        log.info("调用接口【图片详情分页查询】");
        try {
            if(null == param.getId()){
                return RespBean.error("不能为空!");
            }
            RespBean respBean = imageService.selectImageDetailById(param.getId());
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
     * 查看图片详情  根据名称分组
     * @param param
     * @return
     */
    @RequestMapping("/selectImageDetailGroupName")
    @ResponseBody
    public RespBean selectImageDetailGroupName(@RequestBody ImageParam param) {
        log.info("调用接口【查看图片详情-名称分组】");
        try {
            if(null == param.getId()){
                return RespBean.error("不能为空!");
            }
            RespBean respBean = imageService.selectImageDetailGroupName(param.getId());
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
     * 删除图片 主图
     * @param id 主键
     * @return
     */
    @RequestMapping("/delImageMain")
    @ResponseBody
    public RespBean delImageMain(@RequestBody Integer id) {
        try {
            if (null == id) {
                return RespBean.error("主键不能为空!");
            }
            imageService.delImageMain(id);
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
     * 修改主图
     * @param param
     * @return
     */
    @RequestMapping("/updateMain")
    @ResponseBody
    public RespBean updateImageMain(@RequestBody ImageParam param) {
        try {
            if (null == param.getId()) {
                return RespBean.error("主键不能为空!");
            }
            if(null == param.getName()){
                return RespBean.error("标题不能为空!");
            }
            imageService.updateImageMain(param);
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
     * 图片详情 保存
     */
    @RequestMapping("/saveDetail")
    @ResponseBody
    public RespBean saveImageDetail(@RequestBody ImageParam param) {
        try {
            //图片信息保存至 数据库
            imageDetailService.saveImageDetail(param);
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
     * 图片详情 保存
     * 该方法无需验证 图片详情唯一性
     */
    @RequestMapping("/saveDetailByGrade")
    @ResponseBody
    public RespBean saveDetailByGrade(@RequestBody ImageParam param) {
        try {
            if (StringUtil.isEmpty(param.getDetailPath())) {
                return RespBean.error("图片信息不能为空!");
            }
            //图片信息保存至 数据库
            imageDetailService.saveDetailByGrade(param);
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
     * 图片详情 删除
     * @param id
     * @return
     */
    @RequestMapping("/delImageDetail")
    @ResponseBody
    public RespBean delImageDetail(@RequestBody Integer id) {
        try {
            if (null == id) {
                return RespBean.error("主键不能为空!");
            }
            imageDetailService.delImageDetail(id);
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
     * 图片详情 修改
     * @param param
     * @return
     */
    @RequestMapping("/updateImageDetail")
    @ResponseBody
    public RespBean updateImageDetail(@RequestBody ImageParam param) {
        try {
            if (null == param.getId()) {
                return RespBean.error("主键不能为空!");
            }
            if(null == param.getName()){
                return RespBean.error("标题不能为空!");
            }
            imageDetailService.updateImageDetail(param);
            return RespBean.success("修改成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("删除失败!");
        }
    }


}
