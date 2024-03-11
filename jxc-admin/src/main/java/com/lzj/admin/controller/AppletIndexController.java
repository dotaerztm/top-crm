package com.lzj.admin.controller;

import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.*;
import com.lzj.admin.service.IAppletUserService;
import com.lzj.admin.service.IAppletWorksService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
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


    /**
     * 小程序登录
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public RespBean login(@RequestBody AppletUserParam param) {
        log.info("调用接口【小程序---登录】");
        try {
            if(StringUtils.isBlank(param.getMobile())){
                return RespBean.error("手机号不能为空!");
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
     * 获取首页轮显图片List
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("selectWheelImageList ")
    public RespBean selectWheelImageList(@RequestBody AppletUserParam param) {
        log.info("调用接口【小程序---登录】");
        try {
            if(StringUtils.isBlank(param.getMobile())){
                return RespBean.error("手机号不能为空!");
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
     * 作品分页查询
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("selectWorksByPage")
    public RespBean selectWorksByPage(@RequestBody AppletIndexParam param) {
        log.info("调用接口【小程序---作品分页查询】");
        try {
            if(null == param.getWorksType()){
                return RespBean.error("作品类别不能为空!");
            }
            RespBean respBean = appletWorksService.selectWorksByPage(param);
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
