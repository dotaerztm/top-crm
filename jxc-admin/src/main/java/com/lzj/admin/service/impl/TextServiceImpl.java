package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.entity.Image;
import com.lzj.admin.entity.ImageDetail;
import com.lzj.admin.entity.Text;
import com.lzj.admin.mapper.TextMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.TextParam;
import com.lzj.admin.service.ITextService;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本文表 服务实现类
 *
 */
@Service
public class TextServiceImpl extends ServiceImpl<TextMapper, Text> implements ITextService {


    /**
     * 保存文本信息
     * @param param
     */
    @Override
    public void saveText(TextParam param) {
        Text text = this.paramToEntity(param);
        text.setInsertTime(new Date());
        AssertUtil.isTrue(!(this.save(text)),"文本保存失败!");

    }

    /**
     * 修改 文字信息
     * @param param
     */
    @Override
    public void updateText(TextParam param) {
        Text entity = this.baseMapper.selectOne(new QueryWrapper<Text>().eq("is_del",0)
                .eq("id",param.getId()));
        AssertUtil.isTrue(null == entity,"无该视频信息!");
        Text text = this.paramToEntity(param);
        text.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(text)),"文本修改失败!");
    }

    /**
     * 修改 文字信息 By name
     * @param param
     */
    public void updateTextByName(TextParam param) {
        Text entity = this.baseMapper.selectOne(new QueryWrapper<Text>()
                .eq("is_del",0)
                .eq("text_type",param.getType())
                .eq("text_name",param.getName())
        );
        if(null != entity){
            entity.setTextName(param.getNewName());
            entity.setClassTimeContent(param.getClassTimeContent());
            entity.setAssignmentMiddle(param.getAssignmentMiddle());
            entity.setAssignmentContent(param.getAssignmentContent());
            entity.setAssignmentLink(param.getAssignmentLink());
            entity.setUpdateTime(new Date());
            AssertUtil.isTrue(!(this.updateById(entity)),"文本修改失败!");
        }
    }


    /**
     * 删除 图片信息
     * @param id
     */
    @Override
    public void delText(Integer id) {
        Text entity = this.baseMapper.selectOne(new QueryWrapper<Text>().eq("is_del",0)
                .eq("id",id));
        AssertUtil.isTrue(null == entity,"无该信息!");
        Text text = new Text();
        text.setId(id);
        text.setIsDel(1);
        text.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(text)),"删除失败!");
    }

    /**
     * param 转 entity
     * @param param
     * @return
     */
    private Text paramToEntity(TextParam param){
        Text text = new Text();
        text.setId(param.getId());
        text.setContent(param.getContent());
        text.setTextType(param.getType());
        text.setTextName(param.getName());
        text.setRemarks(param.getRemarks());
        text.setLink(param.getLink());
        text.setTextGroup(param.getGroup());
        text.setTextTime(param.getTime());
        text.setClassTimeContent(param.getClassTimeContent());
        text.setAssignmentMiddle(param.getAssignmentMiddle());
        text.setAssignmentContent(param.getAssignmentContent());
        text.setAssignmentLink(param.getAssignmentLink());
        return text;
    }

    /**
     * 分页查询 视频集合
     * @param param
     * @return
     */
    @Override
    public RespBean selectTextByPage(TextParam param) {
        Page<Text> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<Text> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("text_type",param.getType())
                .eq("is_del",0)
                .orderByAsc("sort");
        IPage<Text> pageList = this.page(page, queryWrapper);
        Integer count = this.count(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list",pageList.getRecords());
        map.put("count",count);
        return RespBean.success("成功",map);
    }


    /**
     * 查询文字 根据主图标题
     * @param name
     * @param type
     * @return
     */
    public Text selectImageDetailListByName(String name, Integer type){
        Text entity =  baseMapper.selectOne(new QueryWrapper<Text>().eq("is_del",0)
                .eq("text_name",name).eq("text_type",type));
        return entity;
    }

    /**
     * 查询文字 根据类型
     * @param type
     * @return
     */
    public List<Text> selectTextListByType(Integer type){
        List<Text> list =  baseMapper.selectList(new QueryWrapper<Text>().eq("is_del",0)
                .eq("text_type",type));
        return list;
    }

}
