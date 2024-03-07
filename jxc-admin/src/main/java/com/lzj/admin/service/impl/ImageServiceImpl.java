package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.entity.ImageDetail;
import com.lzj.admin.entity.Text;
import com.lzj.admin.enums.TypeEnum;
import com.lzj.admin.mapper.ImageMapper;
import com.lzj.admin.entity.Image;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.ImagePO;
import com.lzj.admin.po.ImageParam;
import com.lzj.admin.po.TextParam;
import com.lzj.admin.service.IMageService;
import com.lzj.admin.utils.AssertUtil;
import com.lzj.admin.utils.StringUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 图片表 服务实现类
 *
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IMageService {

    @Autowired
    ImageDetailServiceImpl imageDetailServiceImpl;

    @Autowired
    TextServiceImpl textServiceImpl;

    @Autowired
    private MapperFacade mapperFacade;

    /**
     * 保存原始图片到本地
     * @param file
     * @param filePath
     * @return
     * @throws IOException
     */
    @Override
    public  String saveFileToLocal(MultipartFile file ,String filePath) throws IOException {
        InputStream inputStream = null;
        File fileTemp = null;

        fileTemp = File.createTempFile("temp", null);
        // 把multipartFile写入临时文件
        file.transferTo(fileTemp);
        // 使用文件创建 inputStream 流
        inputStream = new FileInputStream(fileTemp);

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        // 构建新的唯一图片名称
        String uniqueImageName = timeStamp + "_" + file.getOriginalFilename();

        File targetFile = new File(filePath, uniqueImageName);

        OutputStream outputStream = new FileOutputStream(targetFile);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();

        return filePath + uniqueImageName;
    }

    /**
     * 保存 图片信息到数据库
     * @param param
     */
    @Override
    public void saveImageToDataBase(ImageParam param) {
        Image image = this.paramToEntity(param);
        image.setInsertTime(new Date());
        AssertUtil.isTrue(!(this.save(image)),"保存失败!");

    }

    /**
     * 保存 图片信息到数据库  验证图片名称
     * @param param
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveImageToDataBaseCheckName(ImageParam param) {
        //验证该图片名称是否存在
        Image entity = this.baseMapper.selectOne(new QueryWrapper<Image>().eq("is_del",0)
                .eq("file_name",param.getName())
                .eq("file_type", param.getType())
                .orderByAsc("sort"));
        AssertUtil.isTrue(null != entity,"该名称已存在!");

        Image image = this.paramToEntity(param);
        image.setInsertTime(new Date());

        AssertUtil.isTrue(!(this.save(image)),"保存失败!");

        if(StringUtil.isNotEmpty(param.getDetailPath())){
            //保存图片详情
            param.setSort(1);//首图默认为1
            imageDetailServiceImpl.saveImageDetail(param);
        }
        if(StringUtil.isNotEmpty(param.getAssignmentContent()) || StringUtil.isNotEmpty(param.getAssignmentMiddle())){
            //保存 课程文字介绍信息
            TextParam text = new TextParam();
            text.setName(param.getName());
            text.setType(param.getType());
            text.setClassTimeContent(param.getClassTimeContent());
            text.setAssignmentMiddle(param.getAssignmentMiddle());
            text.setAssignmentContent(param.getAssignmentContent());
            text.setAssignmentLink(param.getAssignmentLink());
            textServiceImpl.saveText(text);
        }

    }



    /**
     * 修改 图片信息
     * @param param
     */
    @Override
    public void updateImage(ImageParam param) {

        Image entity = this.baseMapper.selectOne(new QueryWrapper<Image>().eq("is_del",0)
                .eq("id",param.getId()));
        AssertUtil.isTrue(null == entity,"无该信息!");
        if(StringUtils.isNotBlank(param.getClassTimeContent()) || StringUtils.isNotBlank(param.getAssignmentMiddle())){
            TextParam text = new TextParam();
            text.setNewName(param.getName());
            text.setName(entity.getFileName());
            text.setType(param.getType());
            text.setClassTimeContent(param.getClassTimeContent());
            text.setAssignmentMiddle(param.getAssignmentMiddle());
            text.setAssignmentContent(param.getAssignmentContent());
            text.setAssignmentLink(param.getAssignmentLink());
            textServiceImpl.updateTextByName(text);
        }
        Image image = this.paramToEntity(param);
        image.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(image)),"修改失败!");

    }

    /**
     * 删除 图片信息
     * @param id
     */
    @Override
    public void delImage(Integer id) {

        Image entity = this.baseMapper.selectOne(new QueryWrapper<Image>().eq("is_del",0)
                .eq("id",id));
        AssertUtil.isTrue(null == entity,"无该信息!");
        Image image = new Image();
        image.setId(id);
        image.setIsDel(1);
        image.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(image)),"删除失败!");
    }


    /**
     * param 转 entity
     * @param param
     * @return
     */
    private Image paramToEntity(ImageParam param){
        Image image = new Image();
        image.setId(param.getId());
        image.setUrl(param.getPath());
        image.setFileType(param.getType());
        image.setFileName(param.getName());
        image.setRemarks(param.getRemarks());
        image.setLink(param.getLink());
        image.setFileGroup(param.getGroup());
        image.setJob(param.getJob());
        image.setSort(param.getSort());
        image.setAuthor(param.getAuthor());
        image.setFileTime(param.getTime());
        image.setThumbnail(param.getThumbnail());
        image.setIntroduction1(param.getIntroduction1());
        image.setIntroduction2(param.getIntroduction2());
        image.setContactUrl(param.getContactUrl());
        return image;
    }

    /**
     * 分页查询 图片集合
     * @param param
     * @return
     */
    @Override
    public RespBean selectImageByPage(ImageParam param) {
        Page<Image> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<Image> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_type",param.getType())
                .eq("is_del",0)
                .orderByAsc("sort");
        IPage<Image> pageList = this.page(page, queryWrapper);

        Integer count = this.count(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        if(param.getType().equals(TypeEnum.GRADE_ONE.getType()) || param.getType().equals(TypeEnum.GRADE_TWO.getType())
        && pageList.getRecords().size() != 0){
            List<Image> imageList = pageList.getRecords();
            List<ImagePO> POList  = mapperFacade.mapAsList(imageList, ImagePO.class);
            List<Text> textList = textServiceImpl.selectTextListByType(param.getType());
            for(ImagePO PO : POList){
                for(Text text : textList){
                    if(PO.getFileName().equals(text.getTextName())){
                        PO.setClassTimeContent(text.getClassTimeContent());
                        PO.setAssignmentMiddle(text.getAssignmentMiddle());
                        PO.setAssignmentContent(text.getAssignmentContent());
                        PO.setAssignmentLink(text.getAssignmentLink());
                    }
                }
            }
            map.put("list",POList);
        }else{
            map.put("list",pageList.getRecords());
        }

        map.put("count",count);
        return RespBean.success("成功",map);
    }


    @Override
    public String saveThumbnailToLocal(String imageUrl){
            String imagePath = imageUrl; // 替换为你的图片路径
            String thumbnailPath = "D:/ERP/thumbnail/"; // 替换为生成的缩略图路径
            int thumbnailWidth = 200; // 缩略图宽度
            int thumbnailHeight = 200; // 缩略图高度
            try {
                // 读取原始图片
                BufferedImage originalImage = ImageIO.read(new File(imagePath));
                // 创建缩略图
                BufferedImage thumbnailImage = new BufferedImage(thumbnailWidth, thumbnailHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D = thumbnailImage.createGraphics();
                graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                graphics2D.drawImage(originalImage, 0, 0, thumbnailWidth, thumbnailHeight, null);
                graphics2D.dispose();
                // 保存缩略图
                ImageIO.write(thumbnailImage, "jpg", new File(thumbnailPath));
                System.out.println("缩略图已生成并保存成功！");
                return null;
            } catch (IOException e) {
                System.out.println("生成缩略图时发生错误：" + e.getMessage());
            }
        return imagePath;
    }


    /**
     * 删除主图
     * @param id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void delImageMain(Integer id) {

        Image entity = this.baseMapper.selectOne(new QueryWrapper<Image>().eq("is_del",0)
                .eq("id",id));
        AssertUtil.isTrue(null == entity,"无该信息!");
        Image image = new Image();
        image.setId(id);
        image.setIsDel(1);
        image.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(image)),"删除失败!");

        //删除图片详情
        imageDetailServiceImpl.delImageDetailByParentName(entity.getFileName(),entity.getFileType());
    }


    /**
     * 修改 主图片
     * @param param
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateImageMain(ImageParam param) {

        Image entity = this.baseMapper.selectOne(new QueryWrapper<Image>().eq("is_del",0)
                .eq("id",param.getId()));
        AssertUtil.isTrue(null == entity,"无该信息!");

        //验证该图片名称是否存在
        Image checkEntity = this.baseMapper.selectOne(new QueryWrapper<Image>().eq("is_del",0)
                .eq("file_name",param.getName())
                .eq("file_type", entity.getFileType())
                .ne("id", entity.getId())
        );
        AssertUtil.isTrue(null != checkEntity,"该名称已存在!");

        Image image = this.paramToEntity(param);
        image.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(image)),"修改失败!");
        //如果入参标题和库中标题不一致 修改明细中的标题
        if(!entity.getFileName().equals(param.getName())){
            imageDetailServiceImpl.updateImageDetailParentName(entity.getFileName(),param.getName(),entity.getFileType());
        }
    }


    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @Override
    public RespBean selectImageDetailById(Integer id) {
        Image entity = this.baseMapper.selectOne(new QueryWrapper<Image>().eq("is_del",0)
                .eq("id",id));
        AssertUtil.isTrue(null == entity,"无该信息!");
        List<ImageDetail> detailList = imageDetailServiceImpl.selectImageDetailListByName(
                entity.getFileName(),entity.getFileType());
        Map<String, Object> map = new HashMap<>();
        map.put("list",detailList);
        return RespBean.success("成功",map);
    }

    @Override
    public RespBean selectImageDetailGroupName(Integer id) {
        Image entity = this.baseMapper.selectOne(new QueryWrapper<Image>().eq("is_del",0)
                .eq("id",id));
        AssertUtil.isTrue(null == entity,"无该信息!");
        List<ImageDetail> detailList = imageDetailServiceImpl.selectImageDetailListByName(
                entity.getFileName(),entity.getFileType());
        Text textEntity = textServiceImpl.selectImageDetailListByName(
                entity.getFileName(),entity.getFileType());

        Map<String, Object> map = new HashMap<>();
        map.put("name",entity.getFileName());
        map.put("author",entity.getAuthor());
        map.put("introduction1",entity.getIntroduction1());
        map.put("introduction2",entity.getIntroduction2());
        map.put("contactUrl",entity.getContactUrl());
        map.put("classTimeContent",textEntity.getClassTimeContent());
        map.put("assignmentMiddle",textEntity.getAssignmentMiddle());
        map.put("assignmentContent",textEntity.getAssignmentContent());
        map.put("assignmentLink",textEntity.getAssignmentLink());
        map.put("list",detailList);
        return RespBean.success("成功",map);
    }


}
