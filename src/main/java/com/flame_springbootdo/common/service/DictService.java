package com.flame_springbootdo.common.service;

import com.flame_springbootdo.common.domain.DictDO;
import com.flame_springbootdo.system.domain.UserDO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author Flame
 * @Date 2018/10/11 16:39
 * @Version 1.0
 */
public interface DictService {

    List<DictDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    List<DictDO> listType();

    int save(DictDO dict);

    int update(DictDO dict);

    DictDO get(Long id);

    int remove(Long id);

    int batchRemove(Long[] ids);

    /**
     * 获取爱好列表
     * @return
     * @param userDO
     */
    List<DictDO> getHobbyList(UserDO userDO);

    /**
     * 获取性别列表
     * @return
     */
    List<DictDO> getSexList();
}
