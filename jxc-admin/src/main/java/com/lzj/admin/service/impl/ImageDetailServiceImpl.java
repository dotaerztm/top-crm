package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.entity.Image;
import com.lzj.admin.entity.ImageDetail;
import com.lzj.admin.enums.TypeEnum;
import com.lzj.admin.mapper.ImageDetailMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImageAndVideoParam;
import com.lzj.admin.po.ImageParam;
import com.lzj.admin.service.IMageDetailService;
import com.lzj.admin.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 图片表 服务实现类
 *
 */
@Service
public class ImageDetailServiceImpl extends ServiceImpl<ImageDetailMapper, ImageDetail> implements IMageDetailService {

    @Autowired
    ImageAndVideoServiceImpl imageAndVideoServiceImpl;



    /**
     * 保存 图片详情
     * @param param
     */
    @Override
    public void saveImageDetail(ImageParam param) {
        //如果是士气一年级/二年级详情图 需要验证group唯一性
        if(param.getType().equals(TypeEnum.GRADE_ONE.getType()) ||
                param.getType().equals(TypeEnum.GRADE_TWO.getType())){
            QueryWrapper<ImageDetail> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("file_type",param.getType())
                    .eq("parent_name",param.getName())
                    .eq("file_group",param.getGroup())
                    .eq("is_del",0);
            AssertUtil.isTrue(this.count(queryWrapper) > 0,"该Part已存在!");
        }
        ImageDetail imageDetail = this.paramToEntity(param);
        imageDetail.setInsertTime(new Date());
        //没有排序 默认为1
        if(null == param.getSort()){
            imageDetail.setSort(1);
        }
        AssertUtil.isTrue(!(this.save(imageDetail)),"保存失败!");

        //修改多图状态
        this.isMoreJudge(param.getName(),param.getType());
    }

    /**
     * 保存 图片详情
     * @param param
     */
    @Override
    public void saveDetailByGrade(ImageParam param) {
        ImageDetail imageDetail = this.paramToEntity(param);
        imageDetail.setInsertTime(new Date());
        AssertUtil.isTrue(!(this.save(imageDetail)),"保存失败!");
    }

    /**
     * 判断是否修改　多图　状态
     * @param name
     * @param type
     */
    public void isMoreJudge(String name ,Integer type){
        //只有作品模块 需要多图判断
        if(type.equals(TypeEnum.WORKS.getType())){
            QueryWrapper<ImageDetail> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("file_type",type)
                    .eq("parent_name",name)
                    .eq("is_del",0);
            Integer count = this.count(queryWrapper);
            System.out.println("count=="+count);
            if(count == 2){
                //如果当前图片详情数为2 修改为多图状态
                imageAndVideoServiceImpl.updateIsMore(name,type,1);
            }else if(count == 1){
                //如果当前图片详情数为1 取消多图状态
                imageAndVideoServiceImpl.updateIsMore(name,type,0);
            }
        }
    }

    /**
     * 保存 图片详情 - 士气作品 多态方法
     * @param param
     */
    public void saveImageDetail(ImageAndVideoParam param) {
        ImageDetail imageDetail = this.paramToEntity(param);
        imageDetail.setInsertTime(new Date());
        AssertUtil.isTrue(!(this.save(imageDetail)),"保存失败!");

    }




    /**
     * ImageParam 转 entity
     * @param param
     * @return
     */
    private ImageDetail paramToEntity(ImageParam param){
        ImageDetail imageDetail = new ImageDetail();
        imageDetail.setParentName(param.getName());
        imageDetail.setUrl(param.getPath());
        imageDetail.setDetailUrl(param.getDetailPath());
        imageDetail.setFileType(param.getType());
        imageDetail.setSort(param.getSort());
        imageDetail.setFileGroup(param.getGroup());
        imageDetail.setRemarks(param.getRemarks());
        return imageDetail;
    }

    /**
     * ImageAndVideoParam 转 entity
     * @param param
     * @return
     */
    private ImageDetail paramToEntity(ImageAndVideoParam param){
        ImageDetail imageDetail = new ImageDetail();
        imageDetail.setParentName(param.getName());
        imageDetail.setUrl(param.getDetailPath());
        imageDetail.setFileType(param.getType());
        imageDetail.setSort(param.getSort());
        return imageDetail;
    }

    /**
     * 删除图片详情 By fileName & fileType
     * @param fileName
     */
    @Override
    public void delImageDetailByParentName(String fileName, Integer fileType){

        QueryWrapper<ImageDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_type",fileType)
                .eq("parent_name",fileName)
                .eq("is_del",0);
        Integer count = this.count(queryWrapper);
        if(count > 0){
            ImageDetail entity = new  ImageDetail();
            entity.setIsDel(1);
            entity.setUpdateTime(new Date());
            AssertUtil.isTrue(!(this.update(entity,queryWrapper)),"详情删除失败!");
        }

    }

    /**
     * 图片详情页 修改parentName
     * @param oldName
     * @param newName
     * @param fileType
     */
    @Override
    public void updateImageDetailParentName(String oldName,String newName, Integer fileType){
        ImageDetail entity = new  ImageDetail();
        entity.setParentName(newName);
        entity.setUpdateTime(new Date());
        QueryWrapper wrapper = new QueryWrapper<ImageDetail>().eq("is_del",0)
                .eq("parent_name",oldName).eq("file_type",fileType);
        AssertUtil.isTrue(!(this.update(entity,wrapper)),"详情删除失败!");
    }

    /**
     * 详情页 分页查询
     * @param param
     * @return
     */
    @Override
    public RespBean selectImageDetailByPage(ImageParam param) {
        Page<ImageDetail> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<ImageDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_type",param.getType()).eq("is_del",0);
        IPage<ImageDetail> pageList = this.page(page, queryWrapper);

        Integer count = this.count(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list",pageList.getRecords());
        map.put("count",count);
        return RespBean.success("成功",map);
    }





    /**
     * 图片详情 删除
     * @param id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void delImageDetail(Integer id){
        ImageDetail entity = this.baseMapper.selectOne(new QueryWrapper<ImageDetail>().eq("is_del",0)
                .eq("id",id));
        AssertUtil.isTrue(null == entity,"无该信息!");

        QueryWrapper<ImageDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_type",entity.getFileType()).eq("parent_name",entity.getParentName())
                .eq("is_del",0);
        AssertUtil.isTrue(this.count(queryWrapper) <= 1,"至少保留一张详情图!");

        ImageDetail imageDetail = new ImageDetail();
        imageDetail.setId(id);
        imageDetail.setIsDel(1);
        imageDetail.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(imageDetail)),"删除失败!");

        //修改多图状态
        this.isMoreJudge(entity.getParentName(),entity.getFileType());
    }

    /**
     * 图片详情 修改
     * @param param
     */
    @Override
    public void updateImageDetail(ImageParam param){
        ImageDetail entity = this.baseMapper.selectOne(new QueryWrapper<ImageDetail>().eq("is_del",0)
                .eq("id",param.getId()));
        AssertUtil.isTrue((null == entity),"无该信息!");

        ImageDetail imageDetail = new ImageDetail();
        imageDetail.setId(param.getId());
        imageDetail.setUrl(param.getPath());
        imageDetail.setDetailUrl(param.getDetailPath());
        imageDetail.setSort(param.getSort());
        imageDetail.setRemarks(param.getRemarks());
        imageDetail.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(imageDetail)),"修改失败!");
    }

    /**
     * 查询图片详情 根据主图标题
     * @param name
     * @param type
     * @return
     */
    public List<ImageDetail> selectImageDetailListByName(String name,Integer type){
        List<ImageDetail> detailList =  baseMapper.selectList(new QueryWrapper<ImageDetail>().eq("is_del",0)
                .eq("parent_name",name).eq("file_type",type).orderByAsc("sort"));
        return detailList;
    }

}
