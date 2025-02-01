package com.practice.cycle.redis;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("member")
public class Member {

    @Id
    private String id;
    private String name;
    private int age;

    public Member(String name, int age){
        this.name = name;
        this.age = age;
    }

}
