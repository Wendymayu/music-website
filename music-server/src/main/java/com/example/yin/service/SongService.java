package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.BaseResponse;
import com.example.yin.model.domain.SongPo;
import com.example.yin.model.request.SongRequest;
import org.springframework.web.multipart.MultipartFile;

public interface SongService extends IService<SongPo> {

    BaseResponse addSong (SongRequest addSongRequest, MultipartFile mpfile);

    BaseResponse updateSongMsg(SongRequest updateSongRequest);

    BaseResponse updateSongUrl(MultipartFile urlFile, int id);

    BaseResponse updateSongPic(MultipartFile urlFile, int id);

    BaseResponse deleteSong(Integer id);

    BaseResponse allSong();

    BaseResponse songOfSingerId(Integer singerId);

    BaseResponse songOfId(Integer id);

    BaseResponse songOfSingerName(String name);
}
