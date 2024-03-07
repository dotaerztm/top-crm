package com.lzj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.entity.Text;
import com.lzj.admin.entity.Video;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.TextParam;
import com.lzj.admin.po.VideoParam;


public interface ITextService extends IService<Text> {


    /**
     * 保存文本信息
     * @param param
     */
    void saveText(TextParam param);

    /**
     * 修改文本
     * @param param
     */
    void updateText(TextParam param);

    /**
     * 删除文本
     * @param id
     */
    void delText(Integer id);

    /**
     * 分页查询 文本集合
     * @param param
     * @return
     */
    RespBean selectTextByPage(TextParam param);

}
