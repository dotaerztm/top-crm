package com.lzj.admin.controller;


import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.TextParam;
import com.lzj.admin.service.ITextService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 文本 控制层
 */
@Controller
@RequestMapping("/text")
public class TextController {


    @Resource
    private ITextService textService;



    /**
     * 保存文本
     */
    @RequestMapping("/save")
    @ResponseBody
    public RespBean saveText(@RequestBody TextParam textParam){
        try {
            if (null == textParam.getContent()) {
                return RespBean.error("文本信息不能为空!");
            }
            //图片信息保存至 数据库
            textService.saveText(textParam);
            return RespBean.success("文本保存成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("文本保存失败!");
        }
    }

    /**
     * 修改文本
     * @param param
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public RespBean updateText(@RequestBody TextParam param) {
        try {
            if (null == param.getId()) {
                return RespBean.error("主键不能为空!");
            }
            textService.updateText(param);
            return RespBean.success("文本修改成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("文本修改失败!");
        }
    }

    /**
     * 删除本文
     * @param id 主键
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public RespBean delImage(@RequestBody Integer id) {
        try {
            if (null == id) {
                return RespBean.error("主键不能为空!");
            }
            textService.delText(id);
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
     * 文本 分页查询
     * @param param
     * @return
     */
    @RequestMapping("/selectTextByPage")
    @ResponseBody
    public RespBean selectTextByPage(@RequestBody TextParam param) {
        try {
            if(null == param.getType()){
                return RespBean.error("类型不能为空!");
            }
            RespBean respBean = textService.selectTextByPage(param);
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
