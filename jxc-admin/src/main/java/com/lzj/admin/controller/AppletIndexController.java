package com.lzj.admin.controller;

import com.lzj.admin.enums.TypeEnum;
import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletUserParam;
import com.lzj.admin.po.ImageAndVideoParam;
import com.lzj.admin.po.ImageParam;
import com.lzj.admin.po.UserParam;
import com.lzj.admin.service.IAppletUserService;
import com.lzj.admin.service.ITextService;
import com.lzj.admin.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 小程序-首页
 */
@Slf4j
@Controller
@RequestMapping("/appletIndex")
public class AppletIndexController {

    @Resource
    private IAppletUserService appletUserService;

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
                return RespBean.error("您所用的手机号未匹配到士气身份信息,如有疑问请联系XXXX");
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

}
