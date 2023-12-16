package com.example.yin.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 轮播图
 */
@TableName(value = "banner")
@Data
public class BannerPo implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String pic;

    private static final long serialVersionUID = 1L;
}