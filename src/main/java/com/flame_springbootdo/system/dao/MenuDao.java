package com.flame_springbootdo.system.dao;

import com.flame_springbootdo.system.domain.MenuDO;
import javafx.scene.input.Mnemonic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author Flame
 * @Date 2018/9/29 11:32
 * @Version 1.0
 */
@Mapper
public interface MenuDao {

    MenuDO get(Long menuId);

    List<MenuDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(MenuDO menu);

    int update(MenuDO menu);

    int remove(Long menuId);

    int batchRemove(Long[] menuIds);

    List<MenuDO> listMenuByUserId(Long id);

    List<String> listUserPerms(Long id);
}
