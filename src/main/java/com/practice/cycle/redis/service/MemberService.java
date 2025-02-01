package com.practice.cycle.redis.service;

import com.practice.cycle.redis.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


//PersonService(RedisTemplate 방식)
//value 타입이 hash
@Service
@RequiredArgsConstructor
public class MemberService {
    private final RedisTemplate<String, Member> redisTemplate;

    public void create(Member member) {
        redisTemplate.opsForValue().set(member.getId(), member);
        redisTemplate.expire(member.getId(), 20, TimeUnit.SECONDS);
    }

    public Member read(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void update(Member member) {
        create(member);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
