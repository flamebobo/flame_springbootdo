package com.flame_springbootdo.system.dao;

import com.flame_springbootdo.system.domain.UserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Flame
 * @email 994810552@qq.com
 * @date 2018-10-18 16:49:00
 */
@Mapper
public interface UserDao {

UserDO get(Long userId);

    List<UserDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserDO user);

    int update(UserDO user);

    int remove(Long user_id);

    int batchRemove(Long[] userIds);
}
