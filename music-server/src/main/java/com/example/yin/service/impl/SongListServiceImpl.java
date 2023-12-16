package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.BaseResponse;
import com.example.yin.mapper.SongListMapper;
import com.example.yin.model.domain.SongListPo;
import com.example.yin.model.request.SongListRequest;
import com.example.yin.service.SongListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongListPo> implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public BaseResponse updateSongListMsg(SongListRequest updateSongListRequest) {
        SongListPo songListPo = new SongListPo();
        BeanUtils.copyProperties(updateSongListRequest, songListPo);
        if (songListMapper.updateById(songListPo) > 0) {
            return BaseResponse.success("修改成功");
        } else {
            return BaseResponse.error("修改失败");
        }
    }

    @Override
    public BaseResponse deleteSongList(Integer id) {
        if (songListMapper.deleteById(id) > 0) {
            return BaseResponse.success("删除成功");
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    @Override
    public BaseResponse allSongList() {
        return BaseResponse.success(null, songListMapper.selectList(null));
    }

    @Override
    public BaseResponse likeTitle(String title) {
        QueryWrapper<SongListPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",title);
        return BaseResponse.success(null, songListMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse likeStyle(String style) {
        QueryWrapper<SongListPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("style",style);
        return BaseResponse.success(null, songListMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse addSongList(SongListRequest addSongListRequest) {
        SongListPo songListPo = new SongListPo();
        BeanUtils.copyProperties(addSongListRequest, songListPo);
        String pic = "/img/songListPic/123.jpg";
        songListPo.setPic(pic);
        if (songListMapper.insert(songListPo) > 0) {
            return BaseResponse.success("添加成功");
        } else {
            return BaseResponse.error("添加失败");
        }
    }

    @Override
    public BaseResponse updateSongListImg(MultipartFile avatorFile, @RequestParam("id") int id) {
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/songListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
        } catch (IOException e) {
            return BaseResponse.fatal("上传失败" + e.getMessage());
        }
        SongListPo songListPo = new SongListPo();
        songListPo.setId(id);
        songListPo.setPic(imgPath);
        if (songListMapper.updateById(songListPo) > 0) {
            return BaseResponse.success("上传成功", imgPath);
        } else {
            return BaseResponse.error("上传失败");
        }
    }
}
