package com.lzj.admin.controller;

import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.*;
import com.lzj.admin.service.IAppletBannerService;
import com.lzj.admin.service.IAppletUserService;
import com.lzj.admin.service.IAppletWorksService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 小程序-首页
 */
@Slf4j
@CrossOrigin
@Controller
@RequestMapping("/appletIndex")
public class AppletIndexController {

    @Resource
    private IAppletUserService appletUserService;

    @Resource
    private IAppletWorksService appletWorksService;

    @Resource
    private IAppletBannerService appletBannerService;


    /**
     * 小程序登录
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public RespBean login(@RequestBody AppletUserParam param) {
        log.info("调用接口【小程序---登录】");
        try {
            if(StringUtils.isBlank(param.getCode())){
                return RespBean.error("code不能为空!");
            }

            RespBean respBean = appletUserService.login(param);
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
     * 小程序 注册
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("register")
    public RespBean register(@RequestBody AppletUserParam param) {
        log.info("调用接口【小程序---注册】");
        try {
            if(StringUtils.isBlank(param.getUuid())){
                return RespBean.error("uuid不能为空!");
            }
            if(StringUtils.isBlank(param.getCode())){
                return RespBean.error("code不能为空!");
            }

            RespBean respBean = appletUserService.register(param);
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
     * 获取 首页Banner
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("selectBannerByPage")
    public RespBean selectBannerByPage(@RequestBody AppletIndexParam param) {
        log.info("调用接口【小程序---Banner分页】");
        try {
            RespBean respBean = appletBannerService.selectBannerByPage(param);
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
     * Banner 新增
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("saveBanner")
    public RespBean saveBanner(@RequestBody AppletBannerParam param) {
        log.info("调用接口【小程序---新增Banner】");
        try {

            if(StringUtils.isBlank(param.getTitle())){
                return RespBean.error("标题不能为空！");
            }
            if(param.getTitle().length() > 15){
                return RespBean.error("标题最长限制为15个中文字符!");
            }
            if(StringUtils.isBlank(param.getImageUrl())){
                return RespBean.error("图片信息不能为空!");
            }
            appletBannerService.saveBanner(param);
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
     * Banner 修改
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("updateBanner")
    public RespBean updateBanner(@RequestBody AppletBannerParam param) {
        log.info("调用接口【小程序---修改Banner】");
        try {
            if(null == param.getId()){
                return RespBean.error("id不能为空！");
            }
            if(StringUtils.isBlank(param.getTitle())){
                return RespBean.error("标题不能为空！");
            }
            if(param.getTitle().length() > 15){
                return RespBean.error("标题最长限制为15个中文字符!");
            }
            if(StringUtils.isBlank(param.getImageUrl())){
                return RespBean.error("图片信息不能为空!");
            }
            appletBannerService.updateBanner(param);
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
     * 首页-精选圈子
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("selectBestWorksByPage")
    public RespBean selectBestWorksByPage(@RequestBody AppletIndexParam param) {
        log.info("调用接口【小程序---精品圈子查询】");
        try {
            RespBean respBean = appletWorksService.selectBestWorksByPage(param);
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
