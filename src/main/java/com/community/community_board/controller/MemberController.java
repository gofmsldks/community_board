package com.community.community_board.controller;

import com.community.community_board.domain.MemberDTO;
import com.community.community_board.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 메인 페이지
    @GetMapping("/")
    public String index() {
        return "member/index";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup.do")
    public String dispSignup() {
        return "member/signup";
    }

    // 회원가입 처리
    @PostMapping("/user/signup.do")
    public String execSignup(MemberDTO memberDTO) {
        memberService.joinUser(memberDTO);
        return "redirect:/user/login.do";
    }

    // 로그인 페이지
    @GetMapping("/user/login.do")
    public String dispLogin() {
        return "member/login";
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result.do")
    public String dispLoginResult() {
        return "redirect:/board/list.do";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result.do")
    public String dispLogout() {
        return "member/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied.do")
    public String dispDenied() {
        return "member/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info.do")
    public String dispMyInfo() {
        return "member/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin.do")
    public String dispAdmin() {
        return "member/admin";
    }
}
