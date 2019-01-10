package com.flame_springbootdo.system.service.impl;

import com.flame_springbootdo.common.domain.Tree;

import com.flame_springbootdo.common.utils.BuildTree;
import com.flame_springbootdo.system.dao.MenuDao;
import com.flame_springbootdo.system.dao.RoleMenuDao;
import com.flame_springbootdo.system.domain.MenuDO;
import com.flame_springbootdo.system.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author Flame
 * @Date 2018/9/29 11:25
 * @Version 1.0
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuDao menuMapper;

    @Autowired
    RoleMenuDao roleMenuMapper;

    /**
     * 獲取系統菜單
     *
     * @param id
     * @return
     */
    @Override
    public List<Tree<MenuDO>> listMenuTree(Long id) {
        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        List<MenuDO> menuDOS = menuMapper.listMenuByUserId(id);
        for (MenuDO sysMenuDO :menuDOS){
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String,Object> attributes = new HashMap<>(16);
            attributes.put("url",sysMenuDO.getUrl());
            attributes.put("icon",sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        //默認頂級菜單為0，根據數據庫實際情況調整
        List<Tree<MenuDO>> list = BuildTree.buildList(trees,"0");
        return list;
    }

    @Override
    public Tree<MenuDO> getSysMenuTree(Long id) {
        return null;
    }

    @Override
    public Tree<MenuDO> getTree() {
        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        List<MenuDO> menuDOS = menuMapper.list(new HashMap<>(16));
        for (MenuDO sysMenuDO : menuDOS) {
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为0，根据数据库实际情况调整
        Tree<MenuDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<MenuDO> getTree(Long id) {
        // 根据roleId查询权限
        List<MenuDO> menus = menuMapper.list(new HashMap<String, Object>(16));
        List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(id);
        List<Long> temp = menuIds;
        for (MenuDO menu : menus) {
            if (temp.contains(menu.getParentId())) {
                menuIds.remove(menu.getParentId());
            }
        }
        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        List<MenuDO> menuDOs = menuMapper.list(new HashMap<String, Object>(16));
        for (MenuDO sysMenuDO : menuDOs) {
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> state = new HashMap<>(16);
            Long menuId = sysMenuDO.getMenuId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<MenuDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public List<MenuDO> list(Map<String, Object> params) {
        return menuMapper.list(params);
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public int remove(Long id) {
        return menuMapper.remove(id);
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public int save(MenuDO menu) {
        return menuMapper.save(menu);
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public int update(MenuDO menu) {
        return  menuMapper.update(menu);
    }

    @Override
    public MenuDO get(Long id) {
        return menuMapper.get(id);
    }

    @Override
    public Set<String> listPerms(Long userId) {
        List<String> perms = menuMapper.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
