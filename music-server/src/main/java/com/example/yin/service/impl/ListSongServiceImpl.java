package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.BaseResponse;
import com.example.yin.mapper.ListSongMapper;
import com.example.yin.model.domain.SongListSongPo;
import com.example.yin.model.request.ListSongRequest;
import com.example.yin.service.ListSongService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl extends ServiceImpl<ListSongMapper, SongListSongPo> implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public List<SongListSongPo> allListSong() {
        return listSongMapper.selectList(null);
    }

    @Override
    public BaseResponse updateListSongMsg(ListSongRequest updateListSongRequest) {
        SongListSongPo listSongPo = new SongListSongPo();
        BeanUtils.copyProperties(updateListSongRequest, listSongPo);
        if (listSongMapper.updateById(listSongPo) > 0) {
            return BaseResponse.success("修改成功");
        } else {
            return BaseResponse.error("修改失败");
        }
    }

    @Override
    public BaseResponse deleteListSong(Integer songId) {
        QueryWrapper<SongListSongPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_id",songId);
        if (listSongMapper.delete(queryWrapper) > 0) {
            return BaseResponse.success("删除成功");
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    @Override
    public BaseResponse addListSong(ListSongRequest addListSongRequest) {
        SongListSongPo listSongPo = new SongListSongPo();
        BeanUtils.copyProperties(addListSongRequest, listSongPo);
        if (listSongMapper.insert(listSongPo) > 0) {
            return BaseResponse.success("添加成功");
        } else {
            return BaseResponse.error("添加失败");
        }
    }

    @Override
    public BaseResponse listSongOfSongId(Integer songListId) {
        QueryWrapper<SongListSongPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        return BaseResponse.success("查询成功", listSongMapper.selectList(queryWrapper));
    }

}
