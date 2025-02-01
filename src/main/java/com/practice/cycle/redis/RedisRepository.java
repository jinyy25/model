package com.practice.cycle.redis;

import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<Member,String> {
}
