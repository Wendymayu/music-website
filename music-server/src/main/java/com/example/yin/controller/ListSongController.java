package com.example.yin.controller;

import com.example.yin.common.BaseResponse;
import com.example.yin.model.request.ListSongRequest;
import com.example.yin.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListSongController {

    @Autowired
    private ListSongService listSongService;


    // 给歌单添加歌曲
    @PostMapping("/listSong/add")
    public BaseResponse addListSong(@RequestBody ListSongRequest addListSongRequest) {
        return listSongService.addListSong(addListSongRequest);
    }

    // 删除歌单里的歌曲
    @GetMapping("/listSong/delete")
    public BaseResponse deleteListSong(@RequestParam int songId) {
        return listSongService.deleteListSong(songId);
    }

    // 返回歌单的歌曲
    @GetMapping("/listSong/detail")
    public BaseResponse listSongOfSongId(@RequestParam int songListId) {
        return listSongService.listSongOfSongId(songListId);
    }

    // 更新歌单里面的歌曲信息
    @PostMapping("/listSong/update")
    public BaseResponse updateListSongMsg(@RequestBody ListSongRequest updateListSongRequest) {
        return listSongService.updateListSongMsg(updateListSongRequest);
    }
}
