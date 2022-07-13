package com.lhl.test;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @author shkstart
 * @create 2022-07-08-16:30
 */
public class PhoneCode {
    static Jedis jedis = new Jedis("192.168.5.100", 6379);
    public static void main(String[] args) {
        //获取6位随机验证码
       //verifyCode("123456");
        getRedisCode("123456","881591");
    }

    //1、获取6位数随机验证码
    public static  String getCode(){
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }

    public static void verifyCode(String phone){
        //连接redis
        //拼接key
        //手机发送次数key
        String countKey = "VerifyCode"+ phone + ":count";
        //验证码key
        String condeKey = "VerifyCode" + phone + ":code";
        //每个随机每天只能发送三次
        String count = jedis.get(countKey);
        if (count == null) {
            //等于null就相当于还没有发送次数，第一次发送
            //在redis中去设置发送次数为1
            jedis.setex(countKey,24*60*60,"1");
        }else if(Integer.parseInt(count)<=2){
            //已经发送过一次了，这是第二次，所以在value值加1
            jedis.incr(countKey);
        }else if ( Integer.parseInt(count) > 2 ){
            //这次是第三次，不能在发送了
            System.out.println("今天发送次数已经超过三次");
            jedis.close();
            return;
        }
        //发送验证码放到redis里面
        String code1 = getCode();
        jedis.setex(condeKey,300,code1);
        jedis.close();
    }

    public static void getRedisCode(String phone,String code){
        //从redis获取验证码
        //验证码key
        String codeKey = "VerifyCode" + phone + ":code";
        String redisCode = jedis.get(codeKey);
        if (redisCode.equals(code)){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
        jedis.close();
    }


}
