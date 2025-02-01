package com.practice.cycle.redis.entity;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor
@Data
@RedisHash(value = "member", timeToLive = 20)
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
