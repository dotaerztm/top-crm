package com.lzj.admin.controller;


import com.lzj.admin.enums.TypeEnum;
import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageParam;
import com.lzj.admin.service.IMageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 团队管理
 */
@Slf4j
@Controller
@RequestMapping("/teamManage")
public class TeamManageController {

    @Resource
    private IMageService imageService;


    /**
     * 创始人列表(页面跳转)
     * @return
     */
    @RequestMapping("founder")
    public String founder(){
        return "teamManage/founder";
    }

    /**
     * 创始人列表-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectImageFromTableByFounder")
    @ResponseBody
    public RespBean selectImageFromTableByFounder(Integer page,Integer limit) {
        log.info("调用接口【图片分页查询-创始人】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageParam param = new  ImageParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.FOUNDER.getType());
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
     * 创始人-新增(页面跳转)
     * @return
     */
    @RequestMapping("founderAdd")
    public String founderAdd(){
        return "teamManage/founderAdd";
    }

    /**
     * 创始人-修改(页面跳转)
     * @return
     */
    @RequestMapping("founderUpdate")
    public String founderUpdate(){
        return "teamManage/founderUpdate";
    }



    /**
     * 核心团队列表(页面跳转)
     * @return
     */
    @RequestMapping("coreTeam")
    public String coreTeam(){
        return "teamManage/coreTeam";
    }

    /**
     * 核心团队列表-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectImageFromTableByCoreTeam")
    @ResponseBody
    public RespBean selectImageFromTableByCoreTeam(Integer page,Integer limit) {
        log.info("调用接口【图片分页查询-核心团队】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            ImageParam param = new  ImageParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setType(TypeEnum.CORE_TEAM.getType());
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
     * 核心团队-新增(页面跳转)
     * @return
     */
    @RequestMapping("coreTeamAdd")
    public String coreTeamAdd(){
        return "teamManage/coreTeamAdd";
    }

    /**
     * 核心团队-修改(页面跳转)
     * @return
     */
    @RequestMapping("coreTeamUpdate")
    public String coreTeamUpdate(){
        return "teamManage/coreTeamUpdate";
    }

}
