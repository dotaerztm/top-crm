package com.lzj.admin.service.impl;

import com.lzj.admin.entity.AppletWorksImage;
import com.lzj.admin.mapper.AppletWorksImageMapper;
import com.lzj.admin.service.IAppletWorksImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 小程序作品图片表 服务实现类
 * </p>
 *
 * @author ch_ztm
 * @since 2024-03-01
 */
@Service
public class AppletWorksImageServiceImpl extends ServiceImpl<AppletWorksImageMapper, AppletWorksImage> implements IAppletWorksImageService {


    public void addWorkImageList(List<AppletWorksImage> list){
        AssertUtil.isTrue(!(this.saveBatch(list)),"保存失败!");
    }

    public void updateWorkImageList(List<AppletWorksImage> list){
        AssertUtil.isTrue(!(this.updateBatchById(list)),"修改失败!");
    }
}
