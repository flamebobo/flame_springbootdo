package com.flame_springbootdo.system.service;

import com.flame_springbootdo.system.domain.UserDO;
import com.flame_springbootdo.system.domain.UserOnline;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface SessionService {
    List<UserOnline> list();

    List<UserDO> listOnlineUser();

    Collection<Session> sessionList();

    boolean forceLogout(String sessionId);
}
