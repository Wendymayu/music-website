package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.BaseResponse;
import com.example.yin.model.domain.SongList;
import com.example.yin.model.request.SongListRequest;
import org.springframework.web.multipart.MultipartFile;

public interface SongListService extends IService<SongList> {

    BaseResponse addSongList(SongListRequest addSongListRequest);

    BaseResponse updateSongListMsg(SongListRequest updateSongListRequest);

    BaseResponse updateSongListImg(MultipartFile avatorFile, int id);

    BaseResponse deleteSongList(Integer id);

    BaseResponse allSongList();

    BaseResponse likeTitle(String title);

    BaseResponse likeStyle(String style);
}
