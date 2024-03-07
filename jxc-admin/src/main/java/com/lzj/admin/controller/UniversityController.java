package com.lzj.admin.controller;


import com.lzj.admin.enums.TypeEnum;
import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageParam;
import com.lzj.admin.service.IMageDetailService;
import com.lzj.admin.service.IMageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 士气大学
 */
@Slf4j
@Controller
@RequestMapping("/university")
public class UniversityController {

    @Resource
    private IMageService imageService;

    @Resource
    private IMageDetailService imageDetailService;


    /**
     * 公益课(页面跳转)
     * @return
     */
    @RequestMapping("publicWelfare")
    public String publicWelfare(){
        return "university/publicWelfare";
    }

    /**
     * 公益课-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectImageFromTableByPublicWelfare")
    @ResponseBody
    public RespBean selectImageFromTableByBusinessDirection(Integer page,Integer limit) {
        log.info("调用接口【图片分页查询-公益课】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageParam param = new  ImageParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.PUBLIC_WELFARE.getType());
            RespBean respBean = imageService.selectImageByPage(param);
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
     * 公益课-新增(页面跳转)
     * @return
     */
    @RequestMapping("publicWelfareAdd")
    public String publicWelfareAdd(){
        return "university/publicWelfareAdd";
    }

    /**
     * 公益课-修改(页面跳转)
     * @return
     */
    @RequestMapping("publicWelfareUpdate")
    public String publicWelfareUpdate(){
        return "university/publicWelfareUpdate";
    }


    /**
     * 分割线 ---------------------------  士气一年级  -----------------------------------------
     */


    /**
     * 士气一年级(页面跳转)
     * @return
     */
    @RequestMapping("gradeOne")
    public String gradeOne(){
        return "university/gradeOne";
    }

    /**
     * 士气一年级 新增(页面跳转)
     * @return
     */
    @RequestMapping("gradeOneAdd")
    public String gradeOneAdd(){
        return "university/gradeOneAdd";
    }

    /**
     * 士气一年级 修改(页面跳转)
     * @return
     */
    @RequestMapping("gradeOneUpdate")
    public String gradeOneUpdate(){
        return "university/gradeOneUpdate";
    }


    /**
     * 士气一年级-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectImageFromTableByGradeOne")
    @ResponseBody
    public RespBean selectImageFromTableByGradeOne(Integer page,Integer limit) {
        log.info("调用接口【图片分页查询---一年级】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageParam param = new  ImageParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.GRADE_ONE.getType());
            RespBean respBean = imageService.selectImageByPage(param);
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
     * 士气一年级 新增Part(页面跳转)
     * @return
     */
    @RequestMapping("gradeOnePartAdd")
    public String gradeOnePartAdd(){
        return "university/gradeOnePartAdd";
    }

    /**
     * 士气一年级 新增Part(页面跳转)
     * @return
     */
    @RequestMapping("gradeOneDetail")
    public String gradeOneDetail(){
        return "university/gradeOneDetail";
    }

    /**
     * 士气一年级-详情页-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectImageDetailFromTableByGradeOne")
    @ResponseBody
    public RespBean selectImageDetailFromTableByGradeOne(Integer page,Integer limit) {
        log.info("调用接口【图片详情分页查询-士气一年级-图片详情页】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageParam param = new  ImageParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.GRADE_ONE.getType());
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
     * 士气一年级 详情页 新增(页面跳转)
     * @return
     */
    @RequestMapping("gradeOneDetailAdd")
    public String gradeOneDetailAdd(){
        return "university/gradeOneDetailAdd";
    }

    /**
     * 士气一年级 详情页 修改(页面跳转)
     * @return
     */
    @RequestMapping("gradeOneDetailUpdate")
    public String gradeOneDetailUpdate(){
        return "university/gradeOneDetailUpdate";
    }



    /**
     * 分割线 ---------------------------  士气二年级  -----------------------------------------
     */


    /**
     * 士气二年级(页面跳转)
     * @return
     */
    @RequestMapping("gradeTwo")
    public String gradeTwo(){
        return "university/gradeTwo";
    }

    /**
     * 士气二年级 新增(页面跳转)
     * @return
     */
    @RequestMapping("gradeTwoAdd")
    public String gradeTwoAdd(){
        return "university/gradeTwoAdd";
    }

    /**
     * 士气二年级 修改(页面跳转)
     * @return
     */
    @RequestMapping("gradeTwoUpdate")
    public String gradeTwoUpdate(){
        return "university/gradeTwoUpdate";
    }


    /**
     * 士气二年级-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectImageFromTableByGradeTwo")
    @ResponseBody
    public RespBean selectImageFromTableByGradeTwo(Integer page,Integer limit) {
        log.info("调用接口【图片分页查询---二年级】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageParam param = new  ImageParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.GRADE_TWO.getType());
            RespBean respBean = imageService.selectImageByPage(param);
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
     * 士气一年级 新增Part(页面跳转)
     * @return
     */
    @RequestMapping("gradeTwoPartAdd")
    public String gradeTwoPartAdd(){
        return "university/gradeTwoPartAdd";
    }

    /**
     * 士气一年级 新增Part(页面跳转)
     * @return
     */
    @RequestMapping("gradeTwoDetail")
    public String gradeTwoDetail(){
        return "university/gradeTwoDetail";
    }

    /**
     * 士气一年级-详情页-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectImageDetailFromTableByGradeTwo")
    @ResponseBody
    public RespBean selectImageDetailFromTableByGradeTwo(Integer page,Integer limit) {
        log.info("调用接口【图片详情分页查询-士气一年级-图片详情页】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageParam param = new  ImageParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.GRADE_TWO.getType());
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
     * 士气一年级 详情页 新增(页面跳转)
     * @return
     */
    @RequestMapping("gradeTwoDetailAdd")
    public String gradeTwoDetailAdd(){
        return "university/gradeTwoDetailAdd";
    }

    /**
     * 士气一年级 详情页 修改(页面跳转)
     * @return
     */
    @RequestMapping("gradeTwoDetailUpdate")
    public String gradeTwoDetailUpdate(){
        return "university/gradeTwoDetailUpdate";
    }


}
