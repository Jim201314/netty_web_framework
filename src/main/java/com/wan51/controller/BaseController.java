package com.wan51.controller;

import java.util.HashMap;

/**
 * Created by gm665 on 2017/3/22.
 */
public class BaseController {

    public static final String ERROR_MSG="系统异常";
    public static final String ERROR_CODE="001";
    public static final String SUCCESS_MSG="成功";
    public static final String SUCCESS_CODE="000";

    protected HashMap error(String code, String msg){
        HashMap res = new HashMap();
        res.put("code", code);
        res.put("msg", msg);
        return res;
    }

    protected HashMap error(){
        return error(ERROR_CODE, ERROR_MSG);
    }

    protected HashMap success(Object obj){
        HashMap res = new HashMap();
        res.put("code", SUCCESS_CODE);
        res.put("msg", SUCCESS_MSG);
        res.put("data", obj);
        return res;
    }


}
