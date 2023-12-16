package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.BaseResponse;
import com.example.yin.constant.Constants;
import com.example.yin.mapper.UserMapper;
import com.example.yin.model.domain.UserPo;
import com.example.yin.model.request.UserRequest;
import com.example.yin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.example.yin.constant.Constants.SALT;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPo>
        implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 新增用户
     */
    @Override
    public BaseResponse addUser(UserRequest registryRequest) {
        if (this.existUser(registryRequest.getUsername())) {
            return BaseResponse.warning("用户名已注册");
        }
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(registryRequest, userPo);
        //MD5加密
        String password = DigestUtils.md5DigestAsHex((SALT + registryRequest.getPassword()).getBytes(StandardCharsets.UTF_8));
        userPo.setPassword(password);
        //都用用
        if (StringUtils.isBlank(userPo.getPhoneNum())) {
            userPo.setPhoneNum(null);
        }
        if ("".equals(userPo.getEmail())) {
            userPo.setEmail(null);
        }
        userPo.setAvator("img/avatorImages/userPo.jpg");
        try {
            if (userMapper.insert(userPo) > 0) {
                return BaseResponse.success("注册成功");
            } else {
                return BaseResponse.error("注册失败");
            }
        } catch (DuplicateKeyException e) {
            return BaseResponse.fatal(e.getMessage());
        }
    }

    @Override
    public BaseResponse updateUserMsg(UserRequest updateRequest) {
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(updateRequest, userPo);
        if (userMapper.updateById(userPo) > 0) {
            return BaseResponse.success("修改成功");
        } else {
            return BaseResponse.error("修改失败");
        }
    }

    @Override
    public BaseResponse updatePassword(UserRequest updatePasswordRequest) {

       if (!this.verityPasswd(updatePasswordRequest.getUsername(),updatePasswordRequest.getOldPassword())) {
            return BaseResponse.error("密码输入错误");
        }

        UserPo userPo = new UserPo();
        userPo.setId(updatePasswordRequest.getId());
        String secretPassword = DigestUtils.md5DigestAsHex((SALT + updatePasswordRequest.getPassword()).getBytes(StandardCharsets.UTF_8));
        userPo.setPassword(secretPassword);

        if (userMapper.updateById(userPo) > 0) {
            return BaseResponse.success("密码修改成功");
        } else {
            return BaseResponse.error("密码修改失败");
        }
    }

    @Override
    public BaseResponse updateUserAvator(MultipartFile avatorFile, int id) {
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //路径 他这个会根据你的系统获取对应的文件分隔符
        String filePath = Constants.ASSETS_PATH + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "avatorImages";
        File file = new File(filePath);
        if (!file.exists() && !file.mkdir()) {
            return BaseResponse.fatal("创建文件失败");
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/avatorImages/" + fileName;
        try {
            avatorFile.transferTo(dest);
        } catch (IOException e) {
            return BaseResponse.fatal("上传失败" + e.getMessage());
        }
        UserPo userPo = new UserPo();
        userPo.setId(id);
        userPo.setAvator(imgPath);
        if (userMapper.updateById(userPo) > 0) {
            return BaseResponse.success("上传成功", imgPath);
        } else {
            return BaseResponse.error("上传失败");
        }
    }

    @Override
    public boolean existUser(String username) {
        QueryWrapper<UserPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return userMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public boolean verityPasswd(String username, String password) {
        QueryWrapper<UserPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        String secretPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes(StandardCharsets.UTF_8));

        queryWrapper.eq("password",secretPassword);
        return userMapper.selectCount(queryWrapper) > 0;
    }

    // 删除用户
    @Override
    public BaseResponse deleteUser(Integer id) {
        if (userMapper.deleteById(id) > 0) {
            return BaseResponse.success("删除成功");
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    @Override
    public BaseResponse allUser() {
        return BaseResponse.success(null, userMapper.selectList(null));
    }

    @Override
    public BaseResponse userOfId(Integer id) {
        QueryWrapper<UserPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return BaseResponse.success(null, userMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse loginStatus(UserRequest loginRequest, HttpSession session) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (this.verityPasswd(username, password)) {
            session.setAttribute("username", username);
            UserPo userPo = new UserPo();
            userPo.setUsername(username);
            return BaseResponse.success("登录成功", userMapper.selectList(new QueryWrapper<>(userPo)));
        } else {
            return BaseResponse.error("用户名或密码错误");
        }
    }
}
