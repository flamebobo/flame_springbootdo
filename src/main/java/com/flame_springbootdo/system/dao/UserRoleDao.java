package com.flame_springbootdo.system.dao;

import com.flame_springbootdo.system.domain.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserRoleDao {
    UserRoleDO get(Long id);

    List<UserRoleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserRoleDO userRole);

    int update(UserRoleDO userRole);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<Long> listRoleId(Long userId);

    int removeByUserId(Long userId);

    int removeByRoleId(Long roleId);

    int batchSave(List<UserRoleDO> list);

    int batchRemoveByUserId(Long[] ids);
}
