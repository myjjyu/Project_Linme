package kr.project.linme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    @GetMapping("/member/findId")
    public String findId() {
        return "member/findId";
    }
    
    @GetMapping("/member/findPw")
    public String findPw() {
        return "member/findPw";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }
}
