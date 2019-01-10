package com.flame_springbootdo.system.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Flame
 * @Date 2018/10/10 14:33
 * @Version 1.0
 */
public class BDSessionListener implements SessionListener {

    //在Java语言中，++i和i++操作并不是线程安全的，在使用的时候，不可避免的会用到synchronized关键字。而AtomicInteger则通过一种线程安全的加减操作接口。十分适合高并发情况下的使用。
    private final AtomicInteger sessionCount = new AtomicInteger(0);

    @Override
    public void onStart(Session session) {
        //对AtomicInteger原子的加1并返回加1后的值
        sessionCount.incrementAndGet();
    }

    @Override
    public void onStop(Session session) {
        //对AtomicInteger原子的减1并返回减1后的值
        sessionCount.decrementAndGet();
    }

    @Override
    public void onExpiration(Session session) {
        sessionCount.decrementAndGet();
    }

    public int getSessionCount() {
        return sessionCount.get();
    }
}
