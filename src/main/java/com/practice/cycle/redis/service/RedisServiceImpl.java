package com.practice.cycle.redis.service;

import com.practice.cycle.redis.entity.Member;
import com.practice.cycle.redis.repository.RedisRepository;
import com.practice.cycle.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {


    private final RedisRepository redisRepository;

    @Autowired
    public RedisServiceImpl (RedisRepository redisRepository){
        this.redisRepository = redisRepository;
    }

    @Override
    public void setRedisData() {
        // 예시로 Member 객체를 생성해서 Redis에 저장하는 로직
        Member member = new Member("springredis", 99);
        member.setId("springredis");
        redisRepository.save(member);  // Redis에 데이터를 저장
    }

}
