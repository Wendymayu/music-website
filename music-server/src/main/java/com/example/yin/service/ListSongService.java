package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.BaseResponse;
import com.example.yin.model.domain.SongListSongPo;
import com.example.yin.model.request.ListSongRequest;

import java.util.List;

public interface ListSongService extends IService<SongListSongPo> {

    BaseResponse addListSong(ListSongRequest addListSongRequest);

    BaseResponse updateListSongMsg(ListSongRequest updateListSongRequest);

    BaseResponse deleteListSong(Integer songId);

    //看看这啥
    List<SongListSongPo> allListSong();

    BaseResponse listSongOfSongId(Integer songListId);
}
