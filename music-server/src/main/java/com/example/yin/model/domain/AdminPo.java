package com.example.yin.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 管理员信息
 */
@TableName(value = "admin")
@Data
public class AdminPo {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String password;
}
