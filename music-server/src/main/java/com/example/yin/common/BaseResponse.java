package com.example.yin.common;

import lombok.Data;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/4 19:04
 **/
@Data
public class BaseResponse {

    private int code;

    private String message;

    private String type;

    private Boolean success;

    private Object data;

    public static BaseResponse success(String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setMessage(message);
        baseResponse.setSuccess(true);
        baseResponse.setType("success");
        baseResponse.setData(null);
        return baseResponse;
    }

    public static BaseResponse success(String message, Object data) {
        BaseResponse baseResponse = success(message);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static BaseResponse warning(String message) {
        BaseResponse baseResponse = error(message);
        baseResponse.setType("warning");
        return baseResponse;
    }

    public static BaseResponse error(String message) {
        BaseResponse baseResponse = success(message);
        baseResponse.setSuccess(false);
        baseResponse.setType("error");
        return baseResponse;
    }

    public static BaseResponse fatal(String message) {
        BaseResponse baseResponse = error(message);
        baseResponse.setCode(500);
        return baseResponse;
    }
}
