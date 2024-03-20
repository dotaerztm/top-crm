package com.lzj.admin.po;


import lombok.Data;

import java.io.Serializable;

@Data
public class PhonePO extends Pagination implements Serializable {

    //用户绑定的手机号（国外手机号会有区号）
    private String phoneNumber;

    //没有区号的手机号
    private String purePhoneNumber;




}
