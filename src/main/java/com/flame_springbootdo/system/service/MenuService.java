package com.flame_springbootdo.system.service;

import com.flame_springbootdo.common.domain.Tree;
import com.flame_springbootdo.system.domain.MenuDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author Flame
 * @Date 2018/9/29 11:24
 * @Version 1.0
 */
@Service
public interface MenuService {

    List<Tree<MenuDO>> listMenuTree(Long id);

    Tree<MenuDO> getSysMenuTree(Long id);

    Tree<MenuDO> getTree();

    Tree<MenuDO> getTree(Long id);

    List<MenuDO> list(Map<String, Object> params);

    int remove(Long id);

    int save(MenuDO menu);

    int update(MenuDO menu);

    MenuDO get(Long id);

    Set<String> listPerms(Long userId);
}
