package com.example.firstproject.controller;

import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;

import java.util.ArrayList;

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
        return "redirect:/members/" + saved.getId();
    }
    @GetMapping("/members/{Id}")
    public String show(@PathVariable Long Id, Model model) {
        Member memberEntity = memberRepository.findById(Id).orElse(null);
        model.addAttribute("member",memberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model) {
        ArrayList<Member> memberEntityList = memberRepository.findAll();
        model.addAttribute("memberList",memberEntityList);
        return "members/index";
    }
    @GetMapping("/members/{Id}/edit")
    public String edit(@PathVariable Long Id,Model model) {
        Member memberEntity = memberRepository.findById(Id).orElse(null);
        model.addAttribute("member",memberEntity);
        return "members/edit";
    }
    @PostMapping("/members/update")
    public String update(MemberForm form) {
        Member memberEntity = form.toEntity();
        log.info(memberEntity.toString());
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        if(target != null) {
            memberRepository.save(memberEntity);
        }
        return "redirect:/members/" + memberEntity.getId();
    }
}
