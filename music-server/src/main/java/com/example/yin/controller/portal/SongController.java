package com.example.yin.controller.portal;

import com.example.yin.common.BaseResponse;
import com.example.yin.model.request.SongRequest;
import com.example.yin.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;

/**
 * 歌曲控制器
 */
@RestController
public class SongController {

    @Autowired
    private SongService songService;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(20, DataUnit.MEGABYTES));
        // 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(20, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }


    // 添加歌曲
    @PostMapping("/song/add")
    public BaseResponse addSong(SongRequest addSongRequest, @RequestParam("file") MultipartFile mpfile) {
        return songService.addSong(addSongRequest, mpfile);
    }

    // 删除歌曲
    @DeleteMapping("/song/delete")
    public BaseResponse deleteSong(@RequestParam int id) {
        return songService.deleteSong(id);
    }

    // 返回所有歌曲
    @GetMapping("/song")
    public BaseResponse allSong() {
        return songService.allSong();
    }

    //TODO ok
    // 返回指定歌曲ID的歌曲
    @GetMapping("/song/detail")
    public BaseResponse songOfId(@RequestParam int id) {
        return songService.songOfId(id);
    }

    // 返回指定歌手ID的歌曲
    @GetMapping("/song/singer/detail")
    public BaseResponse songOfSingerId(@RequestParam int singerId) {
        return songService.songOfSingerId(singerId);
    }

    // 返回指定歌手名的歌曲
    @GetMapping("/song/singerName/detail")
    public BaseResponse songOfSingerName(@RequestParam String name) {
        return songService.songOfSingerName('%' + name + '%');
    }

    // 更新歌曲信息
    @PostMapping("/song/update")
    public BaseResponse updateSongMsg(@RequestBody SongRequest updateSongRequest) {
        return songService.updateSongMsg(updateSongRequest);
    }

    // 更新歌曲图片
    @PostMapping("/song/img/update")
    public BaseResponse updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        return songService.updateSongPic(urlFile, id);
    }

    // 更新歌曲
    @PostMapping("/song/url/update")
    public BaseResponse updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        return songService.updateSongUrl(urlFile, id);
    }
}
