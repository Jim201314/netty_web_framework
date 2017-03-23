package com.wan51.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.wan51.netty.RequestParam;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by 老包子 on 2017/3/22.
 */

public class ApiController extends BaseController {

    public Object test(RequestParam requestParam){
        JSONObject json = JSON.parseObject(requestParam.getData());
        if(json==null)
            return error();
        String username = (String) json.get("username");
        if(!Strings.isNullOrEmpty(username))
            return success(username);
        else
            return success("username is empty");

    }
}
