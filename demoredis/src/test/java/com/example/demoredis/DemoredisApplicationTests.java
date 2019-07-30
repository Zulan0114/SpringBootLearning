package com.example.demoredis;

import com.example.demoredis.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoredisApplicationTests {

	@Test
	public void contextLoads() {
	}

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() throws Exception {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111", 30L, TimeUnit.SECONDS);
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

        stringRedisTemplate.opsForValue().set("bbb", "222", 30L, TimeUnit.SECONDS);
        Assert.assertEquals("222", stringRedisTemplate.opsForValue().get("bbb"));

    }

    @Test
    public void getValue() {
        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @Test
    public void testMyTemplate() {
        // 保存对象
        User user = new User("超人", 20);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        user = new User("蝙蝠侠", 30);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        user = new User("蜘蛛侠", 40);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
        Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
        Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());
    }

}
