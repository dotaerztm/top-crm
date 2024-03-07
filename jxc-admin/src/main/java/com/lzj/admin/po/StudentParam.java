package com.lzj.admin.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信授权
 *
 */
@Data
public class StudentParam extends Pagination implements Serializable {


    private Integer id;

    private String mobile;

}
