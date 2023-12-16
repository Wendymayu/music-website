package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.BaseResponse;
import com.example.yin.mapper.SingerMapper;
import com.example.yin.model.domain.SingerPo;
import com.example.yin.model.request.SingerRequest;
import com.example.yin.service.SingerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, SingerPo> implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public BaseResponse updateSingerMsg(SingerRequest updateSingerRequest) {
        SingerPo singerPo = new SingerPo();
        BeanUtils.copyProperties(updateSingerRequest, singerPo);
        if (singerMapper.updateById(singerPo) > 0) {
            return BaseResponse.success("修改成功");
        } else {
            return BaseResponse.error("修改失败");
        }
    }

    @Override
    public BaseResponse updateSingerPic(MultipartFile avatorFile, int id) {
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singerPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
        } catch (IOException e) {
            return BaseResponse.fatal("上传失败" + e.getMessage());
        }
        SingerPo singerPo = new SingerPo();
        singerPo.setId(id);
        singerPo.setPic(imgPath);
        if (singerMapper.updateById(singerPo) > 0) {
            return BaseResponse.success("上传成功", imgPath);
        } else {
            return BaseResponse.error("上传失败");
        }
    }

    @Override
    public BaseResponse deleteSinger(Integer id) {
        if (singerMapper.deleteById(id) > 0) {
            return BaseResponse.success("删除成功");
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    @Override
    public BaseResponse allSinger() {
        return BaseResponse.success(null, singerMapper.selectList(null));
    }

    @Override
    public BaseResponse addSinger(SingerRequest addSingerRequest) {
        SingerPo singerPo = new SingerPo();
        BeanUtils.copyProperties(addSingerRequest, singerPo);
        String pic = "/img/avatorImages/user.jpg";
        singerPo.setPic(pic);
        if (singerMapper.insert(singerPo) > 0) {
            return BaseResponse.success("添加成功");
        } else {
            return BaseResponse.error("添加失败");
        }
    }

    @Override
    public BaseResponse singerOfName(String name) {
        QueryWrapper<SingerPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return BaseResponse.success(null, singerMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse singerOfSex(Integer sex) {
        QueryWrapper<SingerPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("sex", sex);
        return BaseResponse.success(null, singerMapper.selectList(queryWrapper));
    }
}
