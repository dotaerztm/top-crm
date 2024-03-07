package com.lzj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.entity.Student;
import com.lzj.admin.entity.Wechat;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.StudentParam;
import com.lzj.admin.po.WechatParam;

import java.io.IOException;

/**
 * <p>
 * 学员表 服务类
 * </p>
 *
 */
public interface IStudentService extends IService<Student> {

    /**
     * 学员列表 分页查询
     * @param param
     * @return
     */
    RespBean selectTableByStudent(StudentParam param);

    /**
     * 保存学员
     * @param param
     */
    void saveStudent(StudentParam param);

    /**
     * 删除学员
     * @param id
     */
    void delStudent(Integer id);

}
