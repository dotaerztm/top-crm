package com.lzj.admin.controller;


import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageParam;
import com.lzj.admin.po.VideoParam;
import com.lzj.admin.service.IMageService;
import com.lzj.admin.service.IVideoDetailService;
import com.lzj.admin.service.IVideoService;
import com.lzj.admin.utils.StringUtil;
import com.lzj.admin.utils.UploadAliOSS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 视频表 前端控制器
 */

@Controller
@Slf4j
@RequestMapping("/video")
public class VideoController {

    @Resource
    private IMageService imageService;

    @Resource
    private IVideoService videoService;

    @Resource
    private IVideoDetailService videoDetailService;

    /**
     * 上传视频
     */
    @RequestMapping("/upload")
    @ResponseBody
    public RespBean uploadImage(MultipartFile file){
        try {
            if (null == file.getOriginalFilename()) {
                return RespBean.error("视频信息不能为空!");
            }
            if (!file.getContentType().equals("video/mp4")) {
                return RespBean.error("非视频类型文件!");
            }

            String url = UploadAliOSS.uploadFile(file);
            HashMap<String, Object> map = new HashMap<>();
            map.put("src",url);

            return RespBean.success("视频上传成功!",map);
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("视频上传失败!");
        }

    }

    /**
     * 保存视频
     */
    @RequestMapping("/save")
    @ResponseBody
    public RespBean saveVideo(@RequestBody VideoParam videoParam){
        try {
            if (null == videoParam.getPath()) {
                return RespBean.error("视频信息不能为空!");
            }
            //图片信息保存至 数据库
            videoService.saveVideoToDataBase(videoParam);
            return RespBean.success("视频保存成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("视频保存失败!");
        }
    }

    /**
     * 修改图片
     * @param param
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public RespBean updateVideo(@RequestBody VideoParam param) {
        try {
            if (null == param.getId()) {
                return RespBean.error("主键不能为空!");
            }
            videoService.updateVideo(param);
            return RespBean.success("视频修改成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("视频修改失败!");
        }
    }

    /**
     * 视频 分页查询
     * @param param
     * @return
     */
    @RequestMapping("/selectVideoByPage")
    @ResponseBody
    public RespBean selectVideoByPage(@RequestBody VideoParam param) {
        try {
            if(null == param.getType()){
                return RespBean.error("类型不能为空!");
            }
            RespBean respBean = videoService.selectVideoByPage(param);
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
     * 分割线 ---------------------------  视频详情  -----------------------------------------
     */

    /**
     * 图片详情 分页查询
     * @param param
     * @return
     */
    @RequestMapping("/selectVideoDetailByPage")
    @ResponseBody
    public RespBean selectImageDetailByPage(@RequestBody ImageParam param) {
        log.info("调用接口【图片详情分页查询】");
        try {
            if(null == param.getType()){
                return RespBean.error("类型不能为空!");
            }
            RespBean respBean = videoDetailService.selectVideoDetailByPage(param);
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
            RespBean respBean = videoService.selectVideoDetailById(param.getId());
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
    @RequestMapping("/delVideoMain")
    @ResponseBody
    public RespBean delVideoMain(@RequestBody Integer id) {
        try {
            if (null == id) {
                return RespBean.error("主键不能为空!");
            }
            videoService.delVideoMain(id);
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
     * 图片详情 保存
     */
    @RequestMapping("/saveDetail")
    @ResponseBody
    public RespBean saveImageDetail(@RequestBody ImageParam param) {
        try {
            if (StringUtil.isEmpty(param.getDetailPath())) {
                return RespBean.error("图片信息不能为空!");
            }
            //图片信息保存至 数据库
            videoDetailService.saveVideoDetail(param);
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
            videoDetailService.delVideoDetail(id);
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
            if(null == param.getDetailPath()){
                return RespBean.error("详情图不能为空!");
            }
            videoDetailService.updateVideoDetail(param);
            return RespBean.success("删除成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("删除失败!");
        }
    }

}
