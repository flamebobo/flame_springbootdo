package com.flame_springbootdo.blog.service;

import com.flame_springbootdo.blog.domain.ContentDO;

import java.util.List;
import java.util.Map;

/**
 * @Author Flame
 * @Date 2018/10/31 15:31
 * @Version 1.0
 */

public interface ContentService {

    ContentDO get(Long cid);

    List<ContentDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ContentDO contentDO);

    int update(ContentDO contentDO);

    int remove(Long cid);

    int batchRemove(Long[] cids);
}
