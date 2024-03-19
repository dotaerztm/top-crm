package com.lzj.admin.service.impl;

import com.aliyun.oss.model.DataEncryptionAlgorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.entity.AppletBanner;
import com.lzj.admin.entity.AppletWorks;
import com.lzj.admin.entity.AppletWorksImage;
import com.lzj.admin.mapper.AppletBannerMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.AppletBannerParam;
import com.lzj.admin.po.AppletIndexParam;
import com.lzj.admin.po.AppletWorksParam;
import com.lzj.admin.service.IAppletBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小程序banner表 服务实现类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-19
 */
@Service
public class AppletBannerServiceImpl extends ServiceImpl<AppletBannerMapper, AppletBanner> implements IAppletBannerService {

    @Override
    public RespBean selectBannerByPage(AppletIndexParam param) {
        Page<AppletBanner> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<AppletBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("banner_status",1)
                .le("start_time",new Date()) //<=
                .ge("end_time",new Date())// >=
                .orderByAsc("sort")
                .orderByDesc("insert_time");
        IPage<AppletBanner> pageList = this.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("list",pageList.getRecords());
        map.put("count",pageList.getRecords().size());
        return RespBean.success("成功",map);
    }

    @Override
    public void saveBanner(AppletBannerParam param) {
        AppletBanner entity = new AppletBanner();
        BeanUtils.copyProperties(param, entity);
        entity.setInsertTime(new Date());
        this.save(entity);
    }

    @Override
    public void updateBanner(AppletBannerParam param) {
        AppletBanner entity = new AppletBanner();
        BeanUtils.copyProperties(param, entity);
        entity.setUpdateTime(new Date());
        this.updateById(entity);
    }
}
