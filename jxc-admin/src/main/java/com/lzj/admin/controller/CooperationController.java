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
 * 商业合作
 */
@Slf4j
@Controller
@RequestMapping("/cooperation")
public class CooperationController {

    @Resource
    private IMageService imageService;

    @Resource
    private IMageDetailService imageDetailService;

    /**
     * 商业方向(页面跳转)
     * @return
     */
    @RequestMapping("businessDirection")
    public String businessDirection(){
        return "cooperation/businessDirection";
    }

    /**
     * 商业方向-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectImageFromTableByBusinessDirection")
    @ResponseBody
    public RespBean selectImageFromTableByBusinessDirection(Integer page,Integer limit) {
        log.info("调用接口【图片分页查询-商业方向】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageParam param = new  ImageParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.BUSINESS_DIRECTION.getType());
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
     * 商业方向-新增(页面跳转)
     * @return
     */
    @RequestMapping("businessDirectionAdd")
    public String businessDirectionAdd(){
        return "cooperation/businessDirectionAdd";
    }

    /**
     * 商业方向-修改(页面跳转)
     * @return
     */
    @RequestMapping("businessDirectionUpdate")
    public String businessDirectionUpdate(){
        return "cooperation/businessDirectionUpdate";
    }

    /**
     * 商业方向-详情页(页面跳转)
     * @return
     */
    @RequestMapping("directionDetail")
    public String businessDirectionDetail(){
        return "cooperation/directionDetail";
    }

    /**
     * 商业方向-详情页-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectImageDetailFromTableByDirection")
    @ResponseBody
    public RespBean selectImageDetailFromTableByBusinessDirection(Integer page,Integer limit) {
        log.info("调用接口【图片分页查询-商业方向-详情页】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageParam param = new  ImageParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.BUSINESS_DIRECTION.getType());
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
     * 商业方向-详情页-新增(页面跳转)
     * @return
     */
    @RequestMapping("directionDetailAdd")
    public String directionDetailAdd(){
        return "cooperation/directionDetailAdd";
    }

    /**
     * 商业方向-详情页-修改(页面跳转)
     * @return
     */
    @RequestMapping("directionDetailUpdate")
    public String directionDetailUpdate(){
        return "cooperation/directionDetailUpdate";
    }


    /**
     * 分割线 ---------------------------  品牌方向  -----------------------------------------
     */



    /**
     * 品牌方向(页面跳转)
     * @return
     */
    @RequestMapping("brandDirection")
    public String brandDirection(){
        return "cooperation/brandDirection";
    }

    /**
     * 品牌方向-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectImageFromTableByBrandDirection")
    @ResponseBody
    public RespBean selectImageFromTableByBrandDirection(Integer page,Integer limit) {
        log.info("调用接口【图片分页查询-品牌方向】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageParam param = new  ImageParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.BRAND_DIRECTION.getType());
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
     * 品牌方向-新增(页面跳转)
     * @return
     */
    @RequestMapping("brandDirectionAdd")
    public String brandDirectionAdd(){
        return "cooperation/brandDirectionAdd";
    }

    /**
     * 品牌方向-修改(页面跳转)
     * @return
     */
    @RequestMapping("brandDirectionUpdate")
    public String brandDirectionUpdate(){
        return "cooperation/brandDirectionUpdate";
    }

    /**
     * 品牌方向-详情页(页面跳转)
     * @return
     */
    @RequestMapping("brandDetail")
    public String brandDetail(){
        return "cooperation/brandDetail";
    }

    /**
     * 品牌方向-详情页-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectImageDetailFromTableByBrand")
    @ResponseBody
    public RespBean selectImageDetailFromTableByBrand(Integer page,Integer limit) {
        log.info("调用接口【图片分页查询-商业方向-详情页】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageParam param = new  ImageParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.BRAND_DIRECTION.getType());
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
     * 品牌方向-详情页-新增(页面跳转)
     * @return
     */
    @RequestMapping("brandDetailAdd")
    public String brandDetailAdd(){
        return "cooperation/brandDetailAdd";
    }

    /**
     * 品牌方向-详情页-修改(页面跳转)
     * @return
     */
    @RequestMapping("brandDetailUpdate")
    public String brandDetailUpdate(){
        return "cooperation/brandDetailUpdate";
    }


}
