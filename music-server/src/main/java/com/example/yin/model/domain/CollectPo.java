package com.example.yin.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 收藏
 */
@TableName(value = "collect")
@Data
public class CollectPo {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Byte type;

    private Integer songId;

    private Integer songListId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
