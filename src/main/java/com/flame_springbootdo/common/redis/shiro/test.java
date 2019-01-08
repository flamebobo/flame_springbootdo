package com.flame_springbootdo.common.redis.shiro;
import java.util.Date;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author Flame
 * @Date 2018/12/8 11:33
 * @Version 1.0
 */
@SuppressWarnings("resource")
public class test {

    /*public static void main(String[] arg){
        long start = System.currentTimeMillis();
        usePipeline();
        long end = System.currentTimeMillis();
        System.out.println("usePipeline:"+(end - start));

        start = System.currentTimeMillis();
        withoutPipeline();
        end = System.currentTimeMillis();
        System.out.println("withoutPipeline:"+(end - start));
    }
    private static void withoutPipeline() {
        try {
            Jedis jedis = new Jedis("192.168.157.129", 6379);
            for (int i = 0; i < 100000; i++) {
                jedis.incr("test1");
            }
            jedis.disconnect();
        } catch (Exception e) {
        }
    }

    private static void usePipeline() {
        try {
            Jedis jedis = new Jedis("192.168.157.129", 6379);
            Pipeline pipeline = jedis.pipelined();
            for (int i = 0; i < 100000; i++) {
                pipeline.incr("test2");
            }
            pipeline.sync();
            jedis.disconnect();
        } catch (Exception e) {
        }
    }*/
    public static void main(String[] args) {
     /*   //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.157.129");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob","13023232","12315465");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,20);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }*/

    }

}
