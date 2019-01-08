package com.flame_springbootdo.common.dao;

import com.flame_springbootdo.common.domain.DictDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author Flame
 * @Date 2018/10/11 16:36
 * @Version 1.0
 */
@Mapper
public interface DictDao {
    List<DictDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    List<DictDO> listType();

    int save(DictDO dict);

    int update(DictDO dict);

    DictDO get(Long id);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
