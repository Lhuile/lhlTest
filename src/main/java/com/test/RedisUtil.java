package com.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author shkstart
 * @create 2022-04-27-9:11
 */
public class RedisUtil {
    private static final JedisPool jedisPool;
    static {
        JedisPoolConfig pcfg = new JedisPoolConfig();
        pcfg.setMaxIdle(10);//最大空闲连接数
        pcfg.setMaxTotal(100);//最大的活动连接数

        pcfg.setTestOnBorrow(true);//在获得连接前确定是可用的，如果设置true保证获得的链接时可用的
        //连接池对象 类似于 jdbc DataSource
        jedisPool = new JedisPool(pcfg,"127.0.0.1",6379,3000);

    }

    public static Jedis getJedisConn(){
        return jedisPool.getResource();//获得连接
    }

}
