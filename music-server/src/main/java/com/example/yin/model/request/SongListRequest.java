package com.example.yin.model.request;

import lombok.Data;

/**
 * 歌单
 */
@Data
public class SongListRequest {
    private Integer id;

    private String title;

    private String pic;

    private String style;

    private String introduction;
}
