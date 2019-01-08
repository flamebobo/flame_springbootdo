package com.flame_springbootdo.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Flame
 * @Date 2018/10/11 17:07
 * @Version 1.0
 */
public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    private int total;
    private List<?> rows;

    public PageUtils( List<?> rows,int total) {
        this.rows = rows;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
