package com.lhl.test;

import redis.clients.jedis.Jedis;

/**
 * @author shkstart
 * @create 2022-07-08-15:23
 */
public class RedisDemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.5.100", 6379);
        String ping = jedis.ping();
        System.out.println(ping);
        jedis.close();
        //ijiji

    }

}
