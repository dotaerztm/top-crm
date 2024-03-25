package com.lzj.admin.controller;


import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletBannerParam;
import com.lzj.admin.po.AppletFollowParam;
import com.lzj.admin.po.AppletMessageParam;
import com.lzj.admin.po.AppletWorksParam;
import com.lzj.admin.service.IAppletMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 * 我的 前端控制器
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-22
 */
@Slf4j
@CrossOrigin
@Controller
@RequestMapping("/appletMyself")
public class AppletMySelfController {

    @Resource
    private IAppletMessageService appletMessageService;

    /**
     * 保存 通知消息
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("saveMessage")
    public RespBean saveMessage(@RequestBody AppletMessageParam param) {
        log.info("调用接口【小程序---保存通知消息】");
        try {
            if(StringUtils.isBlank(param.getUuid())){
                return RespBean.error("用户标识不能为空!");
            }
            if(StringUtils.isBlank(param.getTargetUuid())){
                return RespBean.error("对方用户标识不能为空!");
            }
            if(null == param.getMessageType()){
                return RespBean.error("通知类型不能为空!");
            }

            appletMessageService.saveMessage(param);
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
     * 消息通知 分页查询
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("selectMessageByPage")
    public RespBean selectMessageByPage(@RequestBody AppletMessageParam param) {
        log.info("调用接口【小程序--消息通知 分页查询】");
        try {
            if(null == param.getUuid()){
                return RespBean.error("用户标示不能为空!");
            }
            if(null == param.getMessageType()){
                return RespBean.error("消息列表不能为空!");
            }
            RespBean respBean = appletMessageService.selectMessageByPage(param);
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
     * 所有未读消息通知数 查询
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("selectAllMessageCount")
    public RespBean selectAllMessageCount(@RequestBody AppletMessageParam param) {
        log.info("调用接口【小程序--所有未读消息通知数】");
        try {
            if(null == param.getUuid()){
                return RespBean.error("用户标示不能为空!");
            }
            RespBean respBean = appletMessageService.selectAllMessageCount(param.getUuid());
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
     * 消息置为已读
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("updateMessageIsRead")
    public RespBean updateMessageIsRead(@RequestBody AppletMessageParam param) {
        log.info("调用接口【小程序---消息置为已读】");
        try {

            if(null == param.getId()){
                return RespBean.error("消息主键不能为空！");
            }
            appletMessageService.updateMessageIsRead(param.getId());
            return RespBean.success("保存成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("保存失败!");
        }
    }

}
