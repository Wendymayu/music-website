package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.BaseResponse;
import com.example.yin.mapper.AdminMapper;
import com.example.yin.model.domain.AdminPo;
import com.example.yin.model.request.AdminRequest;
import com.example.yin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminPo> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public BaseResponse verityPasswd(AdminRequest adminRequest, HttpSession session) {
        QueryWrapper<AdminPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",adminRequest.getUsername());
        queryWrapper.eq("password",adminRequest.getPassword());
        if (adminMapper.selectCount(queryWrapper) > 0) {
            session.setAttribute("name", adminRequest.getUsername());
            return BaseResponse.success("登录成功");
        } else {
            return BaseResponse.error("用户名或密码错误");
        }
    }
}
