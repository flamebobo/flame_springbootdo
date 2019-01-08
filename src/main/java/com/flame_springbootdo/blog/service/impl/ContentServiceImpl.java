package com.flame_springbootdo.blog.service.impl;

import com.flame_springbootdo.blog.dao.ContentDao;
import com.flame_springbootdo.blog.domain.ContentDO;
import com.flame_springbootdo.blog.service.ContentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author Flame
 * @Date 2018/10/31 16:04
 * @Version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

    private ContentDao contentDao;

    @Override
    public ContentDO get(Long cid) {
        return contentDao.get(cid);
    }

    @Override
    public List<ContentDO> list(Map<String, Object> map) {
        return contentDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return contentDao.count(map);
    }

    @Override
    public int save(ContentDO contentDO) {
        return contentDao.save(contentDO);
    }

    @Override
    public int update(ContentDO contentDO) {
        return contentDao.update(contentDO);
    }

    @Override
    public int remove(Long cid) {
        return contentDao.remove(cid);
    }

    @Override
    public int batchRemove(Long[] cids) {
        return contentDao.batchRemove(cids);
    }
}
