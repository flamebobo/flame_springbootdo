package com.flame_springbootdo.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @Author Flame
 * @Date 2018/10/8 11:03
 * @Version 1.0
 */
public class Query extends LinkedHashMap<String,Object> {
    private static final long serialVersionUID = 1L;
    //查询总数
    private int offset;
    //每页条数
    private int limit;

    public Query(Map<String,Object> params){
        this.putAll(params);
        //分页参数
        this.offset = Integer.parseInt(params.get("offset").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("offset",offset);
        this.put("limit",limit);
        this.put("page",offset/limit + 1);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
