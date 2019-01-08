package com.flame_springbootdo.common.utils;

import com.flame_springbootdo.system.domain.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Collection;
import java.util.List;


public class ShiroUtils {
    @Autowired
    public static SessionDAO sessionDAO;

    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static UserDO getUser(){
        Object object = getSubject().getPrincipal();
        return (UserDO)object;
    }

    public static Long getUserId(){
        return getUser().getUserId();
    }

    public static void logout(){
        getSubject().logout();
    }

    public static List<Principal> getPrincipales(){
        List<Principal> principals = null;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return principals;
    }
}
