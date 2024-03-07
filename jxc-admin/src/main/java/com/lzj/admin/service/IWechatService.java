package com.lzj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.entity.User;
import com.lzj.admin.entity.Wechat;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageParam;
import com.lzj.admin.po.WechatParam;

import java.io.IOException;

/**
 * <p>
 * 微信组件 服务类
 * </p>
 *
 * @author 老李
 */
public interface IWechatService extends IService<Wechat> {


    /**
     * H5登录授权
     * @param code
     * @param signUrl
     * @return
     */
    RespBean wechatLogin(String code,String signUrl);


    void saveForwardImgAndMobile(WechatParam param);

    /**
     * 春节H5用户 分页查询
     * @param param
     * @return
     */
    RespBean selectTableBySpringFestival(WechatParam param);

    /**
     * 修改学员状态
     * @param id
     */
    void updateStudentStatus(Integer id);
}
