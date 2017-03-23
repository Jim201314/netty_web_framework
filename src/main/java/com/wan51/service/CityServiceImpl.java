package com.wan51.service;

import com.wan51.mapper.CityMapper;
import com.wan51.model.City;
import com.wan51.model.CityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 老包子 on 2017/3/23.
 */

@Service("cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    CityMapper cityMapper;

    @Override
    public List<City> listCities() {
        CityExample cityExample = new CityExample();
        return cityMapper.selectByExample(cityExample);
    }
}
