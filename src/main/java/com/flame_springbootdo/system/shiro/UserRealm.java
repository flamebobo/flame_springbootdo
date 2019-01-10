package com.flame_springbootdo.system.shiro;

import com.flame_springbootdo.common.config.ApplicationContextRegister;
import com.flame_springbootdo.common.utils.ShiroUtils;
import com.flame_springbootdo.system.dao.UserDao;
import com.flame_springbootdo.system.domain.UserDO;
import com.flame_springbootdo.system.service.MenuService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author Flame
 * @Date 2018/10/9 14:38
 * @Version 1.0
 */
public class UserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Long userId = ShiroUtils.getUserId();
        MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
        //Set接口：存储无序的，不可重复的元素。---相当于高中的“集合”概念
        //List接口：存储有序的，可以重复的元素.---相当于“动态”数组
        Set<String> perms = menuService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("username",username);
        String password = new String((char[]) authenticationToken.getCredentials());
        UserDao userMapper = ApplicationContextRegister.getBean(UserDao.class);
        // 查询用户信息
        UserDO user = userMapper.list(map).get(0);

        //账户不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        //密码不正确
        if (!password.equals(user.getPassword())) {
            throw new UnknownAccountException("账号或密码不正确~~~");
        }
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定，请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
