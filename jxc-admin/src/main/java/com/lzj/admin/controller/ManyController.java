package com.lzj.admin.controller;


import com.lzj.admin.enums.TypeEnum;
import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageAndVideoParam;
import com.lzj.admin.po.ImageParam;
import com.lzj.admin.po.TextParam;
import com.lzj.admin.service.IMageAndVideoService;
import com.lzj.admin.service.IMageDetailService;
import com.lzj.admin.service.ITextService;
import com.lzj.admin.service.IVideoDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 士气众
 */
@Slf4j
@Controller
@RequestMapping("/many")
public class ManyController {

    @Resource
    private ITextService textService;

    @Resource
    private IMageAndVideoService imageAndVideoService;

    @Resource
    private IMageDetailService imageDetailService;

    @Resource
    private IVideoDetailService videoDetailService;



    /**
     * 士气资讯(页面跳转)
     * @return
     */
    @RequestMapping("information")
    public String information(){
        return "many/information";
    }

    /**
     * 士气咨询-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectTextFromTableByInformation")
    @ResponseBody
    public RespBean selectTextFromTableByInformation(Integer page,Integer limit) {
        log.info("调用接口【文本分页查询-士气资讯人】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            TextParam param = new  TextParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.INFORMATION.getType());
            RespBean respBean = textService.selectTextByPage(param);
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
     * 士气咨询-新增(页面跳转)
     * @return
     */
    @RequestMapping("informationAdd")
    public String informationAdd(){
        return "many/informationAdd";
    }

    /**
     * 士气咨询-修改(页面跳转)
     * @return
     */
    @RequestMapping("informationUpdate")
    public String informationUpdate(){
        return "many/informationUpdate";
    }


    /**
     * 分割线 ---------------------------  士气作品 -----------------------------------------
     */


    /**
     * 士气作品(页面跳转)
     * @return
     */
    @RequestMapping("works")
    public String works(){
        return "many/works";
    }

    /**
     * 士气作品-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectImageAndVideoFromTableByWorks")
    @ResponseBody
    public RespBean selectImageAndVideoFromTableByWorks(Integer page,Integer limit) {
        log.info("调用接口【文本分页查询-士气作品】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageAndVideoParam param = new  ImageAndVideoParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.WORKS.getType());
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
     * 士气作品-新增图片(页面跳转)
     * @return
     */
    @RequestMapping("worksImageAdd")
    public String worksImageAdd(){
        return "many/worksImageAdd";
    }

    /**
     * 士气作品-新增视频(页面跳转)
     * @return
     */
    @RequestMapping("worksVideoAdd")
    public String worksVideoAdd(){
        return "many/worksVideoAdd";
    }

    /**
     * 士气作品-修改图片(页面跳转)
     * @return
     */
    @RequestMapping("worksImageUpdate")
    public String worksImageUpdate(){
        return "many/worksImageUpdate";
    }

    /**
     * 士气作品-修改视频(页面跳转)
     * @return
     */
    @RequestMapping("worksVideoUpdate")
    public String worksVideoUpdate(){
        return "many/worksVideoUpdate";
    }








    /**
     * 士气作品-图片详情(页面跳转)
     * @return
     */
    @RequestMapping("worksDetailImage")
    public String worksDetailImage(){
        return "many/worksDetailImage";
    }

    /**
     * 士气作品-图片详情页-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectImageDetailFromTableByWorks")
    @ResponseBody
    public RespBean selectImageDetailFromTableByWorks(Integer page,Integer limit) {
        log.info("调用接口【图片分页查询-士气作品-图片详情页】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageParam param = new  ImageParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.WORKS.getType());
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
     * 士气作品-图片详情-新增(页面跳转)
     * @return
     */
    @RequestMapping("worksDetailImageAdd")
    public String worksDetailImageAdd(){
        return "many/worksDetailImageAdd";
    }

    /**
     * 士气作品-图片详情-修改(页面跳转)
     * @return
     */
    @RequestMapping("worksDetailImageUpdate")
    public String worksDetailImageUpdate(){
        return "many/worksDetailImageUpdate";
    }



    /**
     * 士气作品-图片详情页-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectVideoDetailFromTableByWorks")
    @ResponseBody
    public RespBean selectVideoDetailFromTableByWorks(Integer page,Integer limit) {
        log.info("调用接口【图片分页查询-士气作品-视频详情页】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageParam param = new  ImageParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.WORKS.getType());
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
     * 士气作品-图片详情(页面跳转)
     * @return
     */
    @RequestMapping("worksDetailVideo")
    public String worksDetailVideo(){
        return "many/worksDetailVideo";
    }
}
