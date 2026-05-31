package com.sky.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class SpringDataRedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate() {
        System.out.println(redisTemplate);
        ValueOperations valueOperations = redisTemplate.opsForValue();//操作字符串
        HashOperations hashOperations = redisTemplate.opsForHash();//操作hash
        ListOperations listOperations = redisTemplate.opsForList();//操作list
        SetOperations setOperations = redisTemplate.opsForSet();//操作set
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();//操作zset
    }

    /**
     * 测试redis操作字符串
     */
    @Test
    public void testString() {
        //set get setex setnx
        redisTemplate.opsForValue().set("city", "北京");
        System.out.println(redisTemplate.opsForValue().get("city"));

        redisTemplate.opsForValue().set("city", "上海", 3, TimeUnit.MINUTES);
        redisTemplate.opsForValue().setIfAbsent("city", "深圳");//setnx

    }

    /**
     * 测试redis操作hash
     */
    @Test
    public void testHash() {
        HashOperations hashOperations = redisTemplate.opsForHash();

        hashOperations.put("user:1", "name", "张三");//hset
        hashOperations.put("user:1", "age", 18);
        System.out.println(hashOperations.get("user:1", "name"));

        //hashOperations.delete("user:1", "name");
        Set keys = hashOperations.keys("user:1");
        System.out.println(keys);

        List values = hashOperations.values("user:1");
        System.out.println(values);


    }

    /**
     * 测试redis操作list
     */
    @Test
    public void testList() {
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPush("list", "张三");
        listOperations.leftPushAll("list", "a","b","c");//插入多个

        List list = listOperations.range("list", 0, -1);
        System.out.println(list);

        listOperations.rightPop("list");

        Long list1 = listOperations.size("list");
        System.out.println(list1);
    }

    /*
    通用命令操作
     */
    @Test
    public void testCommon() {
        Set keys = redisTemplate.keys("*");
        System.out.println(keys);

        Boolean name = redisTemplate.hasKey("name");
        System.out.println(name);

        for(Object key:keys){
            DataType type = redisTemplate.type(key);
            System.out.println(type.name());
        }
    }
}
