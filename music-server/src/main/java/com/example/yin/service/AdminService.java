package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.BaseResponse;
import com.example.yin.model.domain.Admin;
import com.example.yin.model.request.AdminRequest;

import javax.servlet.http.HttpSession;

public interface AdminService extends IService<Admin> {

    BaseResponse verityPasswd(AdminRequest adminRequest, HttpSession session);
}
