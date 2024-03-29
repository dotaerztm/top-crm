package com.lzj.admin.controller;

import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.*;
import com.lzj.admin.service.*;
import com.lzj.admin.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collections;

/**
 * 小程序-圈子
 */
@Slf4j
@CrossOrigin
@Controller
@RequestMapping("/appletCircle")
public class AppletCircleController {

    @Resource
    private IAppletUserService appletUserService;

    @Resource
    private IAppletWorksService appletWorksService;

    @Resource
    private IAppletFollowService appletFollowService;

    @Resource
    private IAppletFansService appletFansService;

    @Resource
    private IAppletWorksLikeService appletWorksLikeService;


    /**
     * 保存作品
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("saveWorks")
    public RespBean saveWorks(@RequestBody AppletWorksParam param) {
        log.info("调用接口【小程序---保存作品】");
        try {
            if(StringUtils.isBlank(param.getUuid())){
                return RespBean.error("用户标识不能为空!");
            }
            if(StringUtils.isNotBlank(param.getTitle()) && param.getTitle().length() > 20){
                return RespBean.error("标题最长限制为20个中文字符!");
            }
            if(StringUtils.isNotBlank(param.getContent()) && param.getTitle().length() > 300){
                return RespBean.error("标题最长限制为2300个中文字符!");
            }
            if(!CollectionUtils.isEmpty(param.getWorksImageList()) && param.getWorksImageList().size() > 9){
                return RespBean.error("图片最多上传9张!");
            }
            appletWorksService.saveWorks(param);
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
     * 修改作品
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("updateWorks")
    public RespBean updateWorks(@RequestBody AppletWorksParam param) {
        log.info("调用接口【小程序---修改作品】");
        try {
            if(null == param.getId()){
                return RespBean.error("主键不能为空!");
            }
            if(StringUtils.isBlank(param.getUuid())){
                return RespBean.error("用户标识不能为空!");
            }
            if(StringUtils.isNotBlank(param.getTitle()) && param.getTitle().length() > 20){
                return RespBean.error("标题最长限制为20个中文字符!");
            }
            if(StringUtils.isNotBlank(param.getContent()) && param.getTitle().length() > 300){
                return RespBean.error("标题最长限制为20个中文字符!");
            }
            if(!CollectionUtils.isEmpty(param.getWorksImageList()) && param.getWorksImageList().size() > 9){
                return RespBean.error("图片最多上传9张!");
            }
            appletWorksService.updateWorks(param);
            return RespBean.success("修改成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("修改失败!");
        }
    }

    /**
     * 删除作品
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("delWorks")
    public RespBean delWorks(@RequestBody AppletWorksParam param) {
        log.info("调用接口【小程序---删除作品】");
        try {
            if(null == param.getId()){
                return RespBean.error("主键不能为空!");
            }
            if(StringUtils.isBlank(param.getUuid())){
                return RespBean.error("用户标识不能为空!");
            }
            appletWorksService.delWorks(param);
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
     * 圈子 作品分页查询
     * @param param
     * @return
     */
//    @ResponseBody
//    @RequestMapping("selectWorksByPage")
//    public RespBean selectWorksByPage(@RequestBody AppletWorksParam param) {
//        log.info("调用接口【小程序--圈子作品 分页查询】");
//        try {
//
//            RespBean respBean = appletWorksService.selectWorksByPage(param);
//            return respBean;
//        } catch (ParamsException e) {
//            e.printStackTrace();
//            return RespBean.error(e.getMsg());
//        }catch (Exception e) {
//            e.printStackTrace();
//            return RespBean.error("查询失败!");
//        }
//    }




    /**
     * 关注
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("follow")
    public RespBean follow(@RequestBody AppletFollowParam param) {
        log.info("调用接口【小程序---关注】");
        try {
            if(StringUtils.isBlank(param.getUuid())){
                return RespBean.error("用户标识不能为空!");
            }
            if(StringUtils.isBlank(param.getFollowUuid())){
                return RespBean.error("用户标识不能为空!");
            }

            RespBean respBean = appletFollowService.follow(param);
            return respBean;
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("修改失败!");
        }
    }



    /**
     * 关注列表 分页查询
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("selectFollowByPage")
    public RespBean selectFollowByPage(@RequestBody AppletFollowParam param) {
        log.info("调用接口【小程序--关注列表 分页查询】");
        try {
            if(null == param.getUuid()){
                return RespBean.error("用户标示不能为空!");
            }
            RespBean respBean = appletFollowService.selectFollowByPage(param);
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
     * 粉丝列表 分页查询
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("selectFansByPage")
    public RespBean selectFansByPage(@RequestBody AppletFollowParam param) {
        log.info("调用接口【小程序--粉丝列表 分页查询】");
        try {
            if(null == param.getUuid()){
                return RespBean.error("用户标示不能为空!");
            }
            RespBean respBean = appletFansService.selectFansByPage(param);
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
     * 查看 单个作品
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("selectWorksById")
    public RespBean selectWorksById(@RequestBody AppletWorksParam param) {
        log.info("调用接口【小程序--单个作品 查询】");
        try {
            if(null == param.getId()){
                return RespBean.error("作品id不能为空!");
            }
            if(StringUtils.isBlank(param.getUuid())){
                return RespBean.error("用户标示不能为空!");
            }
            RespBean respBean = appletWorksService.selectWorksById(param);
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
     * 点赞
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("worksLike")
    public RespBean worksLike(@RequestBody AppletWorksLikeParam param) {
        log.info("调用接口【小程序---点赞】");
        try {
            if(StringUtils.isBlank(param.getUuid())){
                return RespBean.error("用户标识不能为空!");
            }
            if(null == param.getWorksId()){
                return RespBean.error("作品id不能为空!");
            }
            if(null == param.getLikeStatus()){
                return RespBean.error("状态不能为空!");
            }
            appletWorksLikeService.worksLike(param);
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
