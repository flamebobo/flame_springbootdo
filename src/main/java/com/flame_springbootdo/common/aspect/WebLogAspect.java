package com.flame_springbootdo.common.aspect;

import com.flame_springbootdo.common.utils.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author Flame
 * @Date 2018/10/12 10:34
 * @Version 1.0
 */
//定义切面，一个切面可以包括一个或者多个通知
@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 配置切点
     */
    @Pointcut("execution( * com.flame_springbootdo..controller.*.*(..))")
    //第一个*是代表任意修饰符及任意返回值，两个..代表所有子目录，第二个*是代表包下的所有类，第三是类里面的所有方法，最后括号里的两个..代表所有参数
    public void logPointCut(){

    }

    /**
     * 前置通知
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("logPointCut()")//@Before 表示在目标方法执行之前执行;标记的方法的方法体;里面的是切入点表达式.
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录下请求内容
        logger.info("请求地址：" + request.getRequestURL().toString());
        //http 方式
        logger.info("HTTP METHOD : " + request.getMethod());
        //获取ip地址
        logger.info("IP ADDRESS ：" + IPUtils.getIpAddr(request));
        //CLASS METHOD
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "-->" +joinPoint.getSignature().getName());
        //参数
        logger.info("参数 ：" + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 返回通知
     * 无论是连接点是正常返回还是抛出异常，后置通知都会执行，如果只想在连接点返回的时候记录日志，应使用返回通知代替后置通知
     * 在返回通知中, 只要将 returning 属性添加到@AfterReturning 注解中, 就可以访问连接点的返回值.该属性的值即为用来传入返回值的参数名称.
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")//returning的值和doAfterReturning的参数名一致
    public void doAfterReturning(Object ret) throws Throwable{
        //处理完请求，返回内容（返回值太复杂时，打印的是物理存储空间的地址）
        logger.debug("返回值 ：" + ret);
    }

    /**
     * 环绕通知（围绕着方法执行）
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob为方法的返回值
        logger.info("耗时 ：" + (System.currentTimeMillis()-startTime));
        return ob;
    }

    /**
     * 异常通知（只在连接点抛出异常时才执行异常通知）
     *
     * @param joinPoint
     * @param e
     * @throws Throwable
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, ArithmeticException e) throws Throwable{
        logger.info("An exception : " + e + " has been throwing in " + joinPoint.getSignature().getName() + "{}");
    }


}
