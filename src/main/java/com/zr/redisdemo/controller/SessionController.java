package com.zr.redisdemo.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api
@RestController
public class SessionController {

    @GetMapping("setSession")
    public String setSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        session.setAttribute("request Url", request.getRequestURL());
        session.setAttribute("token", UUID.randomUUID());
        return JSONObject.toJSONString(session);
    }

    @GetMapping("getSession")
    public String getSession(HttpServletRequest request) {
        return request.getSession().getAttribute("token").toString();
    }
}
