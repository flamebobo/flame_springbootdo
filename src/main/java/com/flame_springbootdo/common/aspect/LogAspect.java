package com.flame_springbootdo.common.aspect;

import com.flame_springbootdo.common.annotation.Log;
import com.flame_springbootdo.common.utils.HttpContextUtils;
import com.flame_springbootdo.common.utils.IPUtils;
import com.flame_springbootdo.common.utils.JSONUtils;
import com.flame_springbootdo.common.utils.ShiroUtils;
import com.flame_springbootdo.system.domain.LogDO;
import com.flame_springbootdo.system.domain.UserDO;
import com.flame_springbootdo.system.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author Flame
 * @Date 2018/10/8 10:57
 * @Version 1.0
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    LogService logService;

    @Pointcut("@annotation(com.flame_springbootdo.common.annotation.Log)")
    public void logPointCut(){

    }

    @Around("logPointCut()")
    public Object arroud(ProceedingJoinPoint point) throws Throwable{
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogDO sysLog = new LogDO();
        Log syslog = method.getAnnotation(Log.class);
        if (syslog != null) {
            //注解上的描述
            sysLog.setOperation(syslog.value());
        }
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName );
        //请求的参数
        Object[] args = joinPoint.getArgs();
        //有可能会抛出异常，....以后要try catch
        try {
            String params = JSONUtils.beanToJson(args[0]).substring(0,4999);
            sysLog.setParams(params);
        } catch (Exception e) {
            logger.info("这是啥异常："+ e );
        }
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置ip地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        //用户名
        UserDO currUser = ShiroUtils.getUser();
        if (currUser == null) {
            if (sysLog.getParams() != null) {
                sysLog.setUserId(-1L);
                sysLog.setUsername(sysLog.getParams());
            } else {
                sysLog.setUserId(-1L);
                sysLog.setUsername("获取用户信息为空");
            }
        } else {
            sysLog.setUserId(ShiroUtils.getUserId());
            sysLog.setUsername(ShiroUtils.getUser().getUsername());
        }
        sysLog.setTime((int) time);
        Date date = new Date();
        sysLog.setGmtCreate(date);
        //保存系统日志
        logService.save(sysLog);
    }

}
