package com.bootdo.system.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/9/27 10:03
 */
@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Bean
    public RedisTemplate redisTemplate(@Qualifier(value = "stringRedisTemplate") RedisTemplate redisTemplate){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    /**
     * 配置缓存-redis
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){

        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);

        Map<String,Long> cacheExpires = new HashMap<>();
        cacheExpires.put("qrcode",100L);

        redisCacheManager.setExpires(cacheExpires);

        return redisCacheManager;
    }

    /**
     * 缓存key生成
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator(){

        KeyGenerator keyGenerator = new KeyGenerator(){
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder stringBuilder = new StringBuilder();
                //类名称
                stringBuilder.append(o.getClass().getSimpleName().toLowerCase());
                stringBuilder.append(".");
                //方法名称
                stringBuilder.append(method.getName().toLowerCase());
                //参数
                stringBuilder.append("[");
                for (Object obj : objects) {
                    stringBuilder.append(obj.toString());
                }
                stringBuilder.append("]");

                return stringBuilder.toString();
            }
        };

        return keyGenerator;
    }

}
