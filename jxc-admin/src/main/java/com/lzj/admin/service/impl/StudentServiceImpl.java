package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.entity.Student;
import com.lzj.admin.mapper.StudentMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.StudentParam;
import com.lzj.admin.service.IStudentService;
import com.lzj.admin.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 学员 服务实现类
 * </p>
 *
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    WechatServiceImpl wechatServiceImpl;

    /**
     * 学员列表 分页查询
     * @param param
     * @return
     */
    @Override
    public RespBean selectTableByStudent(StudentParam param) {
        Page<Student> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(param.getMobile())){
            queryWrapper.eq("mobile",param.getMobile());
        }
        IPage<Student> pageList = this.page(page, queryWrapper);

        Integer count = this.count(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list",pageList.getRecords());
        map.put("count",count);
        return RespBean.success("成功",map);
    }

    /**
     * 通过手机号查询学员是否存在
     * @param mobile
     * @return
     */
    public Integer selectCountByMobile(String mobile){
        Integer count = this.count(new QueryWrapper<Student>().eq("mobile",mobile));
        return count;
    }

    /**
     * 新增学员
     * @param param
     */
    @Override
    public void saveStudent(StudentParam param) {
        Integer count = this.selectCountByMobile(param.getMobile());
        if(count == 0){
            Student entity =  new Student();
            entity.setMobile(param.getMobile());
            entity.setInsertTime(new Date());
            AssertUtil.isTrue(!(this.save(entity)),"新增学员失败!");
            //将春节h5该手机号用户 置为 学员
            wechatServiceImpl.updateStudentStatusByMobile(param.getMobile(),1);
        }
        AssertUtil.isTrue(count > 0,"学员已存在!");
    }


    /**
     * 删除学员
     * @param id
     */
    @Override
    public void delStudent(Integer id) {
        Student entity = this.baseMapper.selectOne(new QueryWrapper<Student>().eq("id",id));
        AssertUtil.isTrue(null == entity,"无该学员信息!");
        AssertUtil.isTrue(!(this.removeById(id)),"删除失败!");
        //将春节h5该手机号用户 置为 非学员
        wechatServiceImpl.updateStudentStatusByMobile(entity.getMobile(),0);
    }
}
