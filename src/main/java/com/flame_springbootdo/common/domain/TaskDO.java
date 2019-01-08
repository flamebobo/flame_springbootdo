package com.flame_springbootdo.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Flame
 * @Date 2018/12/8 13:31
 * @Version 1.0
 */
public class TaskDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //cron表达式
    private String cronExpression;
    //任务调用的方法名
    private String methodName;
    //任务是否有状态
    private String isConcurrent;
    //任务描述
    private String description;
    //更新者
    private String updateBy;
    //任务执行时调用哪个类的方法 包名+类名
    private String beanClass;
    //创建时间
    private Date createDate;
    //任务状态
    private String jobStatus;
    //任务分组
    private String jobGroup;
    //更新时间
    private Date updateDate;
    //创建者
    private String createBy;
    //Spring bean
    private String springBean;
    //任务名
    private String jobName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(String isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getSpringBean() {
        return springBean;
    }

    public void setSpringBean(String springBean) {
        this.springBean = springBean;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
