package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.entity.*;
import com.lzj.admin.mapper.AppletUserMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletIndexParam;
import com.lzj.admin.po.AppletUserParam;
import com.lzj.admin.po.TokenPO;
import com.lzj.admin.po.VideoParam;
import com.lzj.admin.service.IAppletUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小程序用户表 服务实现类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-02-29
 */
@Service
public class AppletUserServiceImpl extends ServiceImpl<AppletUserMapper, AppletUser> implements IAppletUserService {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @Autowired
    WechatServiceImpl wechatServiceImpl;

    //生产
    private  String APP_ID = "wx6fea344070c8036b";
    private  String APP_SECRET = "84c70aed97b683e92f2f16ec31fd2781";


    @Override
    public RespBean login(AppletUserParam param) {
        try {
            //获取token
            TokenPO tokenEntity = wechatServiceImpl.getAccessTokenByApplet(APP_ID, APP_SECRET);
            String accessToken = tokenEntity.getAccess_token();
            System.out.println("accessToken==="+accessToken);
            //获取明文手机号
            TokenPO phoneEntity = wechatServiceImpl.getAppletPhone(accessToken,param.getCode());
            System.out.println("phoneEntity==="+phoneEntity);

            String phone = phoneEntity.getPurePhoneNumber();
            System.out.println("phone==="+phone);

            //查询该手机号 是否在学员名单
            Integer studentCount = studentServiceImpl.selectCountByMobile(phone);
            AssertUtil.isTrue(studentCount == 0, "您所用的手机号未匹配到士气身份信息,如有疑问请联系XXXX!");
            AppletUser userEntity = this.getOne(new QueryWrapper<AppletUser>().eq("mobile", param.getMobile()));
            //第一次登录
            if (null == userEntity) {

            }
            Map<String, Object> map = new HashMap<>();

            return RespBean.success("成功", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RespBean register(AppletUserParam param) {
        try {
            //查询uuid是否存在
//            AppletFans entity = this.baseMapper.selectOne(new QueryWrapper<AppletFans>()
//                    .eq("uuid",uuid)
//                    .eq("fans_uuid", fansUuid));

            //获取token
            TokenPO tokenEntity = wechatServiceImpl.getAccessTokenByApplet(APP_ID, APP_SECRET);
            String accessToken = tokenEntity.getAccess_token();
            System.out.println("accessToken==="+accessToken);
            //获取明文手机号
            TokenPO phoneEntity = wechatServiceImpl.getAppletPhone(accessToken,param.getCode());
            System.out.println("phoneEntity==="+phoneEntity);

            String phone = phoneEntity.getPurePhoneNumber();
            System.out.println("phone==="+phone);

            //查询该手机号 是否在学员名单
            Integer studentCount = studentServiceImpl.selectCountByMobile(phone);
            AssertUtil.isTrue(studentCount == 0, "您所用的手机号未匹配到士气身份信息,如有疑问请联系XXXX!");
            AppletUser userEntity = this.getOne(new QueryWrapper<AppletUser>().eq("mobile", param.getMobile()));



            Map<String, Object> map = new HashMap<>();

            return RespBean.success("成功", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}
