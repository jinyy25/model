package com.practice.cycle.redis.controller;

import com.practice.cycle.redis.entity.Member;
import com.practice.cycle.redis.service.MemberService;
import com.practice.cycle.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public void create(@RequestBody Member member) {
        System.out.println(member.getName());
        memberService.create(member);
    }

    @GetMapping("/read")
    public Member read(String key) {
        return memberService.read(key);
    }

    @DeleteMapping("/delete")
    public void delete(String key) {
        memberService.delete(key);
    }

    @PutMapping("/update")
    public void update(@RequestBody Member member) {
        memberService.update(member);
    }

}
