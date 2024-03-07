package com.lzj.admin.po;


import lombok.Data;

import java.io.Serializable;

@Data
public class TokenParam extends Pagination implements Serializable {

    private static final long serialVersionUID = 1L;


    //code
    private String code;

    //signUrl
    private String signUrl;


}
