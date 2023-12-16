package com.example.yin.model.request;

import lombok.Data;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/6 16:57
 **/
@Data
public class ScoreRequest {
    private Long id;

    private Long songListId;

    private Long consumerId;

    private Integer score;
}
