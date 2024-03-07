package com.lzj.admin.controller;


import com.lzj.admin.enums.TypeEnum;
import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.*;
import com.lzj.admin.service.*;
import com.lzj.admin.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 活动管理
 */
@Slf4j
@Controller
@RequestMapping("/campaign")
public class CampaignController {

    @Resource
    private ITextService textService;

    @Resource
    private IWechatService wechatService;

    @Resource
    private IMageDetailService imageDetailService;

    @Resource
    private IVideoDetailService videoDetailService;


    /**
     * 微信H5登录授权
     * @return
     */
    @RequestMapping("/wechatLogin")
    @ResponseBody
    public RespBean wechatLogin(@RequestBody TokenParam param) {
        log.info("调用接口【H5授权登录-春节H5】");
        try {
            if(null == param.getCode()){
                return RespBean.error("缺少code!");
            }
            RespBean respBean = wechatService.wechatLogin(param.getCode(),param.getSignUrl());
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
     * 上传祈福图片
     * @param param
     * @return
     */
    @RequestMapping("/saveForwardImgAndMobile")
    @ResponseBody
    public RespBean saveForwardImgAndMobile(@RequestBody WechatParam param) {
        try {
            if (StringUtil.isEmpty(param.getForwardImgUrl()) || StringUtil.isEmpty(param.getSocialImgUrl())) {
                return RespBean.error("图片信息不能为空!");
            }else if(null == param.getOpenid()){
                return RespBean.error("用户标识不能为空!");
            }else if(null == param.getMobile()){
                return RespBean.error("手机号不能为空!");
            }
            //图片信息保存至 数据库
            wechatService.saveForwardImgAndMobile(param);
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
     * 春节H5(页面跳转)
     * @return
     */
    @RequestMapping("/springFestival")
    public String springFestival(){
        return "campaign/springFestival";
    }

    /**
     * 春节H5-分页查询
     * @return
     */
    @RequestMapping("selectTableBySpringFestival")
    @ResponseBody
    public RespBean selectTableBySpringFestival(Integer page,Integer limit,String mobile,Integer id) {
        log.info("调用接口【分页查询-春节H5】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            WechatParam param = new  WechatParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setMobile(mobile);
            param.setId(id);
            RespBean respBean = wechatService.selectTableBySpringFestival(param);
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
     * 修改学员状态
     * @param id
     * @return
     */
    @RequestMapping("/updateStudentStatus")
    @ResponseBody
    public RespBean updateStudentStatus(@RequestBody Integer id) {
        try {
            if (null == id) {
                return RespBean.error("id不能为空!");
            }
            wechatService.updateStudentStatus(id);
            return RespBean.success("修改成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("修改失败!");
        }
    }

}
