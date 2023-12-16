package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.BaseResponse;
import com.example.yin.mapper.SongMapper;
import com.example.yin.model.domain.SongPo;
import com.example.yin.model.request.SongRequest;
import com.example.yin.service.SongService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, SongPo> implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public BaseResponse allSong() {
        return BaseResponse.success(null, songMapper.selectList(null));
    }

    @Override
    public BaseResponse addSong(SongRequest addSongRequest, MultipartFile mpfile) {
        SongPo songPo = new SongPo();
        BeanUtils.copyProperties(addSongRequest, songPo);
        String pic = "/img/songPic/tubiao.jpg";
        String fileName = mpfile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "songPo";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            if (!file1.mkdir()) {
                return BaseResponse.fatal("创建文件失败");
            }
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/songPo/" + fileName;
        try {
            mpfile.transferTo(dest);
        } catch (IOException e) {
            return BaseResponse.fatal("上传失败" + e.getMessage());
        }
        songPo.setCreateTime(new Date());
        songPo.setUpdateTime(new Date());
        songPo.setPic(pic);
        songPo.setUrl(storeUrlPath);
        if (songMapper.insert(songPo) > 0) {
            return BaseResponse.success("上传成功", storeUrlPath);
        } else {
            return BaseResponse.error("上传失败");
        }
    }

    @Override
    public BaseResponse updateSongMsg(SongRequest updateSongRequest) {
        SongPo songPo = new SongPo();
        BeanUtils.copyProperties(updateSongRequest, songPo);
        if (songMapper.updateById(songPo) > 0) {
            return BaseResponse.success("修改成功");
        } else {
            return BaseResponse.error("修改失败");
        }
    }

    @Override
    public BaseResponse updateSongUrl(MultipartFile urlFile, int id) {
        String fileName = urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "songPo";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            if (!file1.mkdir()) {
                return BaseResponse.fatal("创建目的文件夹失败");
            }
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/songPo/" + fileName;
        try {
            urlFile.transferTo(dest);
        } catch (IOException e) {
            return BaseResponse.fatal("更新失败" + e.getMessage());
        }
        SongPo songPo = new SongPo();
        songPo.setId(id);
        songPo.setUrl(storeUrlPath);
        if (songMapper.updateById(songPo) > 0) {
            return BaseResponse.success("更新成功", storeUrlPath);
        } else {
            return BaseResponse.error("更新失败");
        }
    }

    @Override
    public BaseResponse updateSongPic(MultipartFile urlFile, int id) {
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            if (!file1.mkdir()) {
                return BaseResponse.fatal("创建文件夹失败");
            }
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/img/songPic/" + fileName;
        try {
            urlFile.transferTo(dest);
        } catch (IOException e) {
            return BaseResponse.fatal("上传失败" + e.getMessage());
        }
        SongPo songPo = new SongPo();
        songPo.setId(id);
        songPo.setPic(storeUrlPath);
        if (songMapper.updateById(songPo) > 0) {
            return BaseResponse.success("上传成功", storeUrlPath);
        } else {
            return BaseResponse.error("上传失败");
        }
    }

    @Override
    public BaseResponse deleteSong(Integer id) {
        if (songMapper.deleteById(id) > 0) {
            return BaseResponse.success("删除成功");
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    @Override
    public BaseResponse songOfSingerId(Integer singerId) {
        QueryWrapper<SongPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("singer_id",singerId);
        return BaseResponse.success(null, songMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse songOfId(Integer id) {
        QueryWrapper<SongPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return BaseResponse.success(null, songMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse songOfSingerName(String name) {
        QueryWrapper<SongPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        return BaseResponse.success(null, songMapper.selectList(queryWrapper));
    }
}
