package com.flame_springbootdo.common.controller;

import com.flame_springbootdo.common.utils.ShiroUtils;
import com.flame_springbootdo.system.domain.UserDO;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    public static UserDO getUser(){
        return ShiroUtils.getUser();
    }

    public static Long getUserId(){
        return getUser().getUserId();
    }

    public static String getUsername(){
        return getUser().getUsername();
    }
}
