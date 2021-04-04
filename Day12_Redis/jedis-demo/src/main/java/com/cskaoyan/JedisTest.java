package com.cskaoyan;

import com.cskaoyan.utils.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {

    @Test
    public void testAdd(){

        Jedis jedis = JedisUtils.getJedis();

        // 操作Redis数据库
        jedis.set("age","18");

        jedis.hset("user","username","张飞");
        jedis.hset("user","password","俺也一样");
        jedis.hset("user","weight","180");


    }

    @Test
    public void testGet(){

        Jedis jedis = JedisUtils.getJedis();
        String name = jedis.get("name");

        System.out.println("name:" + name);

        String username = jedis.hget("user", "username");

        System.out.println("username：" + username);

    }

    @Test
    public void testOther(){

        Jedis jedis = JedisUtils.getJedis();

        // 我们发现Jedis这个客户端的API和我们的命令保持一致，
        // 所以只要我们能够熟悉使用命令，那么对于他的API就可以很熟悉
//        jedis.lpush();
//        jedis.linsert();
//        jedis.sadd();
//        jedis.zadd();
//        jedis.hset();
//        jedis.hget();
//        jedis.hgetAll();

    }
}
