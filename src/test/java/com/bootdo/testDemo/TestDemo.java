package com.bootdo.testDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void jTestDemo() {

        int [] userId = new int[]{1,4587954,123,32,98,234,435834,234,9843,239487,98234,92834};

        for(int  i=0;i<userId.length;i++)
        {
            Long time = System.currentTimeMillis();
            System.out.println(userId[i]*875431%100000);
            String _num = time+""+(userId[i]*875431%100000);

            System.out.println("订单号："+_num+" 长度:"+_num.length());
        }

    }

    @GetMapping("/test/demo")
    String restTest() {
        Integer i = null;
        i++;
        return "hello test";
    }

}
