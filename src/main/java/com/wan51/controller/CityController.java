package com.wan51.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.wan51.model.City;
import com.wan51.netty.RequestParam;
import com.wan51.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 老包子 on 2017/3/22.
 * 提取数据库测试
 */

public class CityController extends BaseController {

    @Autowired
    CityService cityService;

    public Object list(RequestParam requestParam){
        List<City> cities = cityService.listCities();
        return success(cities);
    }
}
