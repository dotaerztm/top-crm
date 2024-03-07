package com.lzj.admin.utils;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 图片视频上传阿里云 OSS存储对象
 */
public class UploadAliOSS {

    public static String uploadFile(MultipartFile file) throws Exception {
        try {
            String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
            String accessKeyId = "LTAI5tKPjJsPsKVmAG9dbV8V";
            String accessKeySecret = "KTiowyTjwqk8fb4b2oEr0CI6m6ybVv";
            String bucketName = "shiqitop";
            String path = "https://shiqitop.oss-cn-shanghai.aliyuncs.com";

            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            // 获取文件名称
            String fileName = file.getOriginalFilename();

            // 以下操作主要是把上传的文件分类放到oss存储，这样防止上传相同的文件名，会把前一个文件覆盖掉。 start
            // 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // yuy76t5rew01.jpg
            fileName = uuid + fileName;
            // 2 把文件按照日期进行分类
            // 获取当前日期,格式化
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String datePath = sdf.format(new Date());
            // 拼接
            fileName = datePath + "/" + fileName;

            // end
            // 调用oss方法实现上传
            // 第一个参数 Bucket名称
            // 第二个参数 上传到oss文件路径和文件名称
            // 第三个参数 上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();

            // 把上传之后文件路径返回，需要把上传到阿里云oss路径手动拼接出来
            String url = path + "/" + fileName;
            return url;
        } catch (Exception e) {
            return "";
        }
    }
}