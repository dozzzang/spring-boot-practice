package com.example.firstproject.controller;

import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members/signup")
    public String newMemberForm() {
        return "/members/new";
    }

    @PostMapping("/join")
    public String joinMember(MemberForm form) {
       // System.out.println(form.toString());
        log.info(form.toString());
        Member member = form.toEntity();
        //System.out.println(member.toString());
        log.info(member.toString());
        Member saved = memberRepository.save(member);
        // System.out.println(saved.toString());
        log.info(saved.toString());
        return "";
    }
}
