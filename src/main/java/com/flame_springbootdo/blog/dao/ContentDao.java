package com.flame_springbootdo.blog.dao;

import com.flame_springbootdo.blog.domain.ContentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author Flame
 * @Date 2018/11/6 10:46
 * @Version 1.0
 */

@Mapper
public interface ContentDao {

    ContentDO get(Long cid);

    List<ContentDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(ContentDO content);

    int update(ContentDO content);

    int remove(Long cid);

    int batchRemove(Long[] cids);
}
