package com.flame_springbootdo.system.controller;

import com.flame_springbootdo.common.utils.R;
import com.flame_springbootdo.system.domain.UserDO;
import com.flame_springbootdo.system.domain.UserOnline;
import com.flame_springbootdo.system.service.SessionService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;

/**
 * @Author Flame
 * @Date 2018/10/25 15:42
 * @Version 1.0
 */
@Controller
@RequestMapping("/sys/online")
public class SessionController {
    @Autowired
    SessionService sessionService;

    @GetMapping()
    public String online(){
        return "system/online/online";
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<UserOnline> list() {
        return sessionService.list();
    }

    /**
     * 暂不用
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listOnlineUser")
    public List<UserDO> listOnlineUser() {
        return sessionService.listOnlineUser();
    }

    @ResponseBody
    @RequestMapping("/forceLogout/{sessionId}")
    public R forceLogout(@PathVariable("sessionId") String sessionId, RedirectAttributes redirectAttributes) {
        try {
            sessionService.forceLogout(sessionId);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }

    }

    @ResponseBody
    @RequestMapping("/sessionList")
    public Collection<Session> sessionList() {
        return sessionService.sessionList();
    }


}

