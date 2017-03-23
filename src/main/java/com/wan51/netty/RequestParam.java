package com.wan51.netty;

/**
 * Created by 老包子 on 2017/3/22.
 */
public class RequestParam {
    //服务器IP
    private String host;
    //客户端token
    private String token;
    //session id
    private String jsessionid;
    //service名称
    private String service;
    //方法名，用于service反射
    private String action;
    //参数, json格式
    private String data;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
