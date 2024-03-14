package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.lzj.admin.entity.Wechat;
import com.lzj.admin.mapper.WechatMapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.po.TokenPO;
import com.lzj.admin.po.WechatParam;
import com.lzj.admin.service.IWechatService;
import com.lzj.admin.utils.AssertUtil;
import com.lzj.admin.utils.EntityToMapConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.lzj.admin.utils.Sign;

;

/**
 * <p>
 * 微信组件 服务实现类
 * </p>
 *
 */
@Service
public class WechatServiceImpl extends ServiceImpl<WechatMapper, Wechat> implements IWechatService {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    //生产
    private static final String APP_ID = "wx63c91432d836528e";
    private static final String APP_SECRET = "2d1f1a15b4bdfd3dea23483dfb4359c2";

    //测试
//    private static final String APP_ID = "wxa8dbd5015a8cb99d";
//    private static final String APP_SECRET = "fc69ec99f52311d937a4e953af7f3343";

    private static final Integer TRUE = 1;
    private static final Integer FALSE = 0;


    /**
     * 微信登录授权 获取用户信息
     * @param code
     * @return
     * @throws IOException
     */
    @Override
    public RespBean wechatLogin(String code,String signUrl) {
        try {
            //根据code 网页授权access_token
            TokenPO tokenEntity = getAccessTokenMap(code);
            String accessToken = tokenEntity.getAccess_token();
            TokenPO userInfo = new TokenPO();
            if(StringUtils.isNotBlank(accessToken)){
                String openId = tokenEntity.getOpenid();
                userInfo = getUserInfo(accessToken, openId);
                userInfo.setAccess_token(accessToken);
                System.out.println("userInfo==="+userInfo);
                //保存授权用户
                Integer id = saveWechatUser(userInfo);
                userInfo.setId(id);
            }else {
                userInfo = tokenEntity;
            }
            //获取签名
            TokenPO sdkEntity =  getAccessTokenByJsSDK();
            String accessTokenSDK = sdkEntity.getAccess_token();
            System.out.println("accessTokenSDK=="+accessTokenSDK);
            TokenPO ticketEntity =  getTicket(accessTokenSDK);
            String ticket = ticketEntity.getTicket();
            System.out.println("ticket=="+ticket);

            Map<String, Object> map = EntityToMapConverter.convert(userInfo);
            //调用鉴权接口
            Map<String, String> signMap = Sign.sign(ticket,signUrl);
            System.out.println("signMap=="+signMap);
            map.put("url",signMap.get("url"));
            map.put("jsapiTicket",signMap.get("jsapi_ticket"));
            map.put("nonceStr",signMap.get("nonceStr"));
            map.put("timestamp",signMap.get("timestamp"));
            map.put("signature",signMap.get("signature"));

            return RespBean.success("成功", map);
        }catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

//    public static void main(String[] args) throws IOException {
//        TokenPO sdkEntity =  getAccessTokenByJsSDK();
//        String accessTokenSDK = sdkEntity.getAccess_token();
//        System.out.println("accessTokenSDK=="+accessTokenSDK);
//        TokenPO ticketEntity =  getTicket(accessTokenSDK);
//        String ticket = ticketEntity.getTicket();
//        System.out.println("ticket=="+ticket);
//
//        Map<String, String> signMap = Sign.sign(ticket,"https://alphatang.cn/demos/act-dragon/?code=041I3iGa1tAiOG08UpIa1tO4q13I3iGc");
//        System.out.println("signMap=="+signMap);
//    }

    /**
     * 保存 授权用户
     * @param po
     */
    private Integer saveWechatUser(TokenPO po){
        if(null != po.getOpenid()){
            Wechat userInfo = this.baseMapper.selectOne(new QueryWrapper<Wechat>().eq("openid",po.getOpenid()));
            Wechat entity = new  Wechat();
            entity.setOpenid(po.getOpenid());
            entity.setNickName(po.getNickname());
            entity.setHeadImgUrl(po.getHeadimgurl());
            if(null == userInfo){
                entity.setInsertTime(new Date());
                AssertUtil.isTrue(!(this.save(entity)),"保存失败!");
                // 获取自动生成的主键ID
            }else{
                entity.setId(userInfo.getId());
                entity.setUpdateTime(new Date());
                AssertUtil.isTrue(!(this.updateById(entity)),"保存失败!");
            }
            return entity.getId();
        }
        return null;
    }



    private TokenPO getAccessTokenMap(String code) throws IOException {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APP_ID +
                "&secret=" + APP_SECRET +
                "&code=" + code +
                "&grant_type=authorization_code";
        return sendGetRequest(url);
    }

    private static TokenPO getAccessTokenByJsSDK() throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID +
                "&secret=" + APP_SECRET;
        return sendGetRequest(url);
    }



    public  TokenPO getAccessTokenByApplet(String appId, String appSecret) throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId +
                "&secret=" + appSecret;
        return sendGetRequest(url);
    }

    public TokenPO getAppletPhone(String accessToken,String code) throws IOException {
        String url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken +
                "&code=" + code;
        return sendGetRequest(url);
    }

    private static TokenPO getTicket(String token) throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + token +
                "&type=jsapi";
        return sendGetRequest(url);
    }

    private TokenPO getUserInfo(String accessToken, String openId) throws IOException {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken +
                "&openid=" + openId;
        return sendGetRequest(url);
    }

    private static TokenPO sendGetRequest(String url) throws IOException {
        URL requestUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            inputStream.close();
            System.out.println("response.toString=="+response);
            return StringToEntity(response.toString());
        } else {
            throw new IOException("HTTP GET request failed with error code: " + responseCode);
        }
    }


    /**
     * 转换 接口response to entity
     * @param response
     * @return
     */
    private static TokenPO StringToEntity(String response){
        Gson gson = new Gson();
        TokenPO entity = gson.fromJson(response, TokenPO.class);
        return entity;
    }


    /**
     * 上传祈福图片
     * @param param
     */
    public void saveForwardImgAndMobile(WechatParam param) {
        Wechat userInfo = this.baseMapper.selectOne(new QueryWrapper<Wechat>().eq("openid",param.getOpenid()));
        AssertUtil.isTrue(null == userInfo,"无该用户信息!");

        //如果手机号存在学员表  非学员不允许参加
        Integer count = studentServiceImpl.selectCountByMobile(param.getMobile());
        System.out.println("count=="+count);
        AssertUtil.isTrue(count == 0,"该手机号无学员记录");

        Wechat mobileInfo = this.baseMapper.selectOne(new QueryWrapper<Wechat>().eq("mobile",param.getMobile()));
        //openid和绑定手机号不是同一个用户  && 其他用户已经上传该手机号
        if(null != mobileInfo && !mobileInfo.getOpenid().equals(param.getOpenid())){
            AssertUtil.isTrue(true,"该手机号已经参加过活动!");
        }

        userInfo.setMobile(param.getMobile());
        userInfo.setForwardImgUrl(param.getForwardImgUrl());
        userInfo.setSocialImgUrl(param.getSocialImgUrl());
        userInfo.setUpdateTime(new Date());
        userInfo.setIsStudent(TRUE);

        AssertUtil.isTrue(!(this.updateById(userInfo)),"上传失败!");
    }

    /**
     * 根据手机号修改学员状态
     * @param mobile
     * @param flag
     */
    public void updateStudentStatusByMobile(String mobile,Integer flag){
        Wechat userInfo = this.baseMapper.selectOne(new QueryWrapper<Wechat>().eq("mobile",mobile));
        if(null != userInfo){
            userInfo.setIsStudent(flag);
            AssertUtil.isTrue(!(this.updateById(userInfo)),"保存失败!");
        }
    }


    @Override
    public RespBean selectTableBySpringFestival(WechatParam param) {
        Page<Wechat> page = new Page<>(param.getPageIndex(), param.getPageSize());
        QueryWrapper<Wechat> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(param.getMobile())){
            queryWrapper.eq("mobile",param.getMobile());
        }
        if(null != param.getId()){
            queryWrapper.eq("id",param.getId());
        }
        IPage<Wechat> pageList = this.page(page, queryWrapper);

        Integer count = this.count(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list",pageList.getRecords());
        map.put("count",count);
        return RespBean.success("成功",map);
    }

    /**
     * 修改学员状态
     * @param id
     */
    public void updateStudentStatus(Integer id) {

        Wechat entity = this.baseMapper.selectOne(new QueryWrapper<Wechat>().eq("id",id));
        AssertUtil.isTrue(null == entity,"无该信息!");

        Integer flag = TRUE;
        if(entity.getIsStudent()==TRUE){
            flag = FALSE;
        }
        entity.setIsStudent(flag);
        entity.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(entity)),"修改失败!");
    }

}
