package com.flame_springbootdo.common.domain;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author Flame
 * @Date 2018/9/30 15:34
 * @Version 1.0
 */
public class FileDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    //文件类型
    private Integer type;
    //URL地址
    private String url;
    //创建时间
    private Date createDate;

    public FileDO(){
        super();
    }

    public FileDO(Integer type, String url, Date createDate){
        super();
        this.type = type;
        this.url = url;
        this.createDate = createDate;

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "FileDO{" +
                "id=" + id +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", createDate=" + createDate +
                '}';
    }

}
