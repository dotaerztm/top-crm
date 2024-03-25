package com.lzj.admin.service;

import com.lzj.admin.entity.AppletMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletFollowParam;
import com.lzj.admin.po.AppletMessageParam;

/**
 * <p>
 * 小程序信息通知表 服务类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-22
 */
public interface IAppletMessageService extends IService<AppletMessage> {

    void saveMessage(AppletMessageParam param);

    RespBean selectMessageByPage(AppletMessageParam param);

    void updateMessageIsRead(Integer id);

    RespBean selectAllMessageCount(String uuid);
}
