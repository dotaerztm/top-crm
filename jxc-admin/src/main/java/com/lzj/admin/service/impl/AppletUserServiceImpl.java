package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.entity.*;
import com.lzj.admin.enums.AppletUserEnum;
import com.lzj.admin.mapper.AppletUserMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletIndexParam;
import com.lzj.admin.po.AppletUserParam;
import com.lzj.admin.po.TokenPO;
import com.lzj.admin.po.VideoParam;
import com.lzj.admin.service.IAppletUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

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
            //根据code获取unionId
            TokenPO unionIdEntity = wechatServiceImpl.getUnioniIdByApplet(APP_ID,APP_SECRET,param.getCode());
            String uuid = unionIdEntity.getUnionid();
            System.out.println("uuid==="+uuid);

            //查询uuid是否存在库中
            AppletUser userEntity = this.getOne(new QueryWrapper<AppletUser>()
                    .eq("uuid", param.getUuid()));

            Map<String, Object> map = new HashMap<>();
            map.put("uuid",param.getUuid());

            //如果有记录 并有手机号
            if(null != userEntity && !StringUtils.isBlank(userEntity.getMobile())){
                map.put("mobile",userEntity.getMobile());
                map.put("status",userEntity.getUserType());
                //如果是游客状态 查看此刻是否在学员名单中
                if(userEntity.getUserType() == AppletUserEnum.TOURIST.getType()){
                    Integer studentCount = studentServiceImpl.selectCountByMobile(userEntity.getMobile());
                    if(studentCount > 0){
                        userEntity.setUserType(AppletUserEnum.STUDENT.getType());
                        userEntity.setUpdateTime(new Date());
                        this.updateById(userEntity);
                        map.put("status",AppletUserEnum.STUDENT.getType());
                    }
                }
            }else{
                map.put("mobile",null);
                map.put("status",AppletUserEnum.TOURIST.getType());
            }
            //首次登录 新增用户记录
            if(null == userEntity){
                AppletUser addEntity = new AppletUser();
                addEntity.setUuid(param.getUuid());
                addEntity.setUserType(AppletUserEnum.TOURIST.getType());
                addEntity.setInsertTime(new Date());
                this.save(addEntity);
            }

            return RespBean.success("成功", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RespBean register(AppletUserParam param) {
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

            Integer userType = studentCount == 0 ? AppletUserEnum.TOURIST.getType() : AppletUserEnum.STUDENT.getType();

            //查询uuid是否存在库中
            AppletUser userEntity = this.getOne(new QueryWrapper<AppletUser>()
                    .eq("uuid", param.getUuid()));
            //首次登录 新增用户记录
            if(null == userEntity){
                AppletUser addEntity = new AppletUser();
                addEntity.setUuid(param.getUuid());
                addEntity.setMobile(phone);
                addEntity.setUserType(userType);
                addEntity.setInsertTime(new Date());
                this.save(addEntity);
            }

            AssertUtil.isTrue(studentCount == 0, "您所用的手机号未匹配到士气身份信息,如有疑问请联系XXXX!");

            //如果存在学员名单 并且 之前登录过  置为 学员用户
            if(null != userEntity && userEntity.getUserType() == AppletUserEnum.TOURIST.getType()){
                userEntity.setUserType(AppletUserEnum.STUDENT.getType());
                userEntity.setMobile(phone);
                userEntity.setUpdateTime(new Date());
                this.updateById(userEntity);
            }

            Map<String, Object> map = new HashMap<>();
            map.put("uuid",param.getUuid());
            map.put("mobile",phone);
            map.put("status",userType);

            return RespBean.success("成功", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}
