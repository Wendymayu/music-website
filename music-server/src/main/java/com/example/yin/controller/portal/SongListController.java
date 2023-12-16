package com.example.yin.controller.portal;

import com.example.yin.common.BaseResponse;
import com.example.yin.model.request.ListSongRequest;
import com.example.yin.model.request.SongListRequest;
import com.example.yin.service.ListSongService;
import com.example.yin.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 歌单控制器
 */
@RestController
public class SongListController {
    @Autowired
    private ListSongService listSongService;

    @Autowired
    private SongListService songListService;


    // 添加歌单
    @PostMapping("/songList/add")
    public BaseResponse addSongList(@RequestBody SongListRequest addSongListRequest) {
        return songListService.addSongList(addSongListRequest);
    }

    // 删除歌单
    @GetMapping("/songList/delete")
    public BaseResponse deleteSongList(@RequestParam int id) {
        return songListService.deleteSongList(id);
    }

    //TODO 这块就是前端显现相应的歌单list
    // 返回所有歌单
    @GetMapping("/songList")
    public BaseResponse allSongList() {
        return songListService.allSongList();
    }

    // 返回标题包含文字的歌单
    @GetMapping("/songList/likeTitle/detail")
    public BaseResponse songListOfLikeTitle(@RequestParam String title) {
        return songListService.likeTitle('%' + title + '%');
    }

    // 返回指定类型的歌单
    @GetMapping("/songList/style/detail")
    public BaseResponse songListOfStyle(@RequestParam String style) {
        return songListService.likeStyle('%' + style + '%');
    }

    // 更新歌单信息
    @PostMapping("/songList/update")
    public BaseResponse updateSongListMsg(@RequestBody SongListRequest updateSongListRequest) {
        return songListService.updateSongListMsg(updateSongListRequest);

    }

    // 更新歌单图片
    @PostMapping("/songList/img/update")
    public BaseResponse updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        return songListService.updateSongListImg(avatorFile, id);
    }

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
