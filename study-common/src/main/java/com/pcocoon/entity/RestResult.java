package com.pcocoon.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZonedDateTime;


/**
 * <p>Description:[Rest请求返回信息vo]</p>
 * Created on: 2019/03/26 23:03
 *
 * @Author <a href="mailto:Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */

@Getter
@Setter
public class RestResult<T> {

    private static final Integer SUCCESS = 200;
    private static final Integer SERVER_ERROR = 500;
    private Integer code;

    private String msg;

    private T data;

    private Instant timestamp;

    private RestResult() {
        this.timestamp = ZonedDateTime.now().toInstant();
    }

    /*******以下为公共方法*********/

    public Boolean isSuccess() {
        if (SUCCESS.equals(this.getCode())) {
            return true;
        }
        return false;
    }

    public static RestResult renderSuccess() {
        RestResult restResult = new RestResult();
        restResult.setCode(SUCCESS);
        return restResult;
    }

    public static RestResult renderSuccess(String msg) {
        RestResult restResult = new RestResult();
        restResult.setCode(SUCCESS);
        restResult.setMsg(msg);
        return restResult;
    }

    public static RestResult renderSuccess(Object data) {
        RestResult restResult = new RestResult();
        restResult.setCode(SUCCESS);
        restResult.setData(data);
        return restResult;
    }

    public static RestResult renderSuccess(String msg, Object data) {
        RestResult restResult = new RestResult();
        restResult.setMsg(msg);
        restResult.setData(data);
        return restResult;
    }

    public static RestResult renderError() {
        RestResult restResult = new RestResult();
        restResult.setCode(SERVER_ERROR);
        return restResult;
    }

    public static RestResult renderError(Integer code) {
        RestResult restResult = new RestResult();
        restResult.setCode(code);
        return restResult;
    }

    public static RestResult renderError(String msg) {
        RestResult restResult = new RestResult();
        restResult.setCode(SERVER_ERROR);
        restResult.setMsg(msg);
        return restResult;
    }

    public static RestResult renderError(Object object) {
        RestResult restResult = new RestResult();
        restResult.setCode(SERVER_ERROR);
        restResult.setData(object);
        return restResult;
    }

    public static RestResult renderError(Integer code, String msg) {
        RestResult restResult = new RestResult();
        restResult.setCode(code);
        restResult.setMsg(msg);
        return restResult;
    }

    public static RestResult renderError(Integer code, Object object) {
        RestResult restResult = new RestResult();
        restResult.setCode(code);
        restResult.setData(object);
        return restResult;
    }

    public static RestResult renderError(String msg, Object object) {
        RestResult restResult = new RestResult();
        restResult.setCode(SERVER_ERROR);
        restResult.setData(object);
        restResult.setMsg(msg);
        return restResult;
    }

    public static RestResult render(Integer code, String msg, Object data) {
        RestResult restResult = new RestResult();
        restResult.setCode(code);
        restResult.setMsg(msg);
        restResult.setData(data);
        return restResult;
    }
}
