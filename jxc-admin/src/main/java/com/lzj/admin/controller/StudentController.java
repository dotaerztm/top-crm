package com.lzj.admin.controller;


import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.StudentParam;
import com.lzj.admin.po.TextParam;
import com.lzj.admin.po.WechatParam;
import com.lzj.admin.service.*;
import com.lzj.admin.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 学员管理
 */
@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {


    @Resource
    private IStudentService studentService;




    /**
     * 学员列表(页面跳转)
     * @return
     */
    @RequestMapping("studentTable")
    public String studentTable(){
        return "campaign/studentTable";
    }


    /**
     * 学员新增(页面跳转)
     * @return
     */
    @RequestMapping("studentAdd")
    public String studentAdd(){
        return "campaign/studentAdd";
    }


    /**
     * 学员列表-分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectTableByStudent")
    @ResponseBody
    public RespBean selectTableByStudent(Integer page,Integer limit,String mobile) {
        log.info("调用接口【分页查询-春节H5】");
        try {
            if(null == page || null == limit){
                return RespBean.error("缺少分页参数!");
            }
            StudentParam param = new  StudentParam();
            param.setPageIndex(page);
            param.setPageSize(limit);
            param.setMobile(mobile);
            RespBean respBean = studentService.selectTableByStudent(param);
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
     * 学员新增
     * @param param
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public RespBean saveStudent(@RequestBody StudentParam param){
        try {
            if (null == param.getMobile()) {
                return RespBean.error("手机号不能为空!");
            }
            //图片信息保存至 数据库
            studentService.saveStudent(param);
            return RespBean.success("学员新增成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("学员新增失败!");
        }
    }

    /**
     * 学员删除
     * @param id
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public RespBean delImage(@RequestBody Integer id) {
        try {
            if (null == id) {
                return RespBean.error("主键不能为空!");
            }
            studentService.delStudent(id);
            return RespBean.success("删除成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("删除失败!");
        }
    }


}
