package kr.project.linme.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    @GetMapping("/member/findId")
    public String findId() {
        return "member/findId";
    }

    @GetMapping("/member/findIdIn")
    public String findIdIn() {
        return "member/findIdIn";
    }
    
    @GetMapping("/member/findPw")
    public String findPw() {
        return "member/findPw";
    }

    @GetMapping("/login")
    public String login(Model model,
        @CookieValue(value = "rememberId", required = false) String rememberId) {
        model.addAttribute("rememberId", rememberId);
        return "login";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    
}
