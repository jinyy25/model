package com.practice.cycle.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("localhost")
    private String host;

    @Value("6379")
    private int port;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        // 기본 직렬화( JSON 형식) Object <=> JSON
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());

        // Key 직렬화 (String 형식)
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // value 직렬화 (JSON 형식)
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 해시 데이터 구조 직렬화 (JSON 형식)
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }
}
