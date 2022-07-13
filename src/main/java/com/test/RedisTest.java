package com.test;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shkstart
 * @create 2022-04-27-9:11
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis conn = RedisUtil.getJedisConn();

        conn.sadd("aihao","看美女","打怪兽");
        Set<String> aihao = conn.smembers("aihao");
        for (String s : aihao) {
            System.out.println(s);
        }

        conn.close();

    }

    private static void hashMapTest(Jedis conn) {
        Map<String, String> user = new HashMap<>();
        user.put("name","zhangsan");
        user.put("AGE","23");
        user.put("sex","nan");

        conn.hmset("user:001",user);

        List<String> hmget = conn.hmget("user:001", "name", "sex", "AGE");
        for (String s : hmget) {
            System.out.println(s);
        }
        String name = conn.hget("user:001","name");
        System.out.println(name);
        conn.close();
    }

    private static void redisList(Jedis conn) {
        conn.lpush("language","english","UFO");
        conn.lpush("language","打怪兽");

        List<String> language = conn.lrange("language", 0, 10);
        for (String s : language) {
            System.out.println(s);
        }
        conn.close();
    }

    private static void getString() {
        Jedis conn = RedisUtil.getJedisConn();
        conn.set("year","2022");//set 存String
        System.out.println(conn.get("year"));
        conn.close();
    }


}
