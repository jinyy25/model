package com.practice.cycle.redis.repository;

import com.practice.cycle.redis.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<Member,String> {
}
