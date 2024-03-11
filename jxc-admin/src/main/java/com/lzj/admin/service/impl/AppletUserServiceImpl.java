package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.entity.AppletUser;
import com.lzj.admin.entity.AppletWorks;
import com.lzj.admin.entity.Student;
import com.lzj.admin.entity.Video;
import com.lzj.admin.mapper.AppletUserMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletIndexParam;
import com.lzj.admin.po.AppletUserParam;
import com.lzj.admin.po.VideoParam;
import com.lzj.admin.service.IAppletUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public RespBean login(AppletUserParam param) {
        //查询该手机号 是否在学员名单
        Integer studentCount = studentServiceImpl.selectCountByMobile(param.getMobile());
        AssertUtil.isTrue(studentCount == 0,"您所用的手机号未匹配到士气身份信息,如有疑问请联系XXXX!");
        AppletUser userEntity = this.getOne(new QueryWrapper<AppletUser>().eq("mobile",param.getMobile()));
        //第一次登录 api接口 根据手机号获取用户信息-uuid
        if(null == userEntity){

        }
        Map<String, Object> map = new HashMap<>();

        return RespBean.success("成功",map);
    }



}
