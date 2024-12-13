package kr.project.linme.controllers;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kr.project.linme.helpers.UtilHelper;
import kr.project.linme.helpers.WebHelper;
import kr.project.linme.services.MemberService;
import lombok.extern.slf4j.Slf4j;
import kr.project.linme.models.Member;



@Slf4j
@Controller
public class UserController {

    @Autowired
    private UtilHelper utilHelper;

    @Autowired
    private WebHelper webHelper;

    @Autowired
    private MemberService memberService;

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

    @GetMapping("/member/resetPw")
    public String resetPw() {
        return "member/resetPw";
    }

@PostMapping("/member/resetPwIn")
    public String resetPwIn(
        Model model,
        @RequestParam("user_name") String userName,
        @RequestParam("user_id") String userId,
        @RequestParam("tel") String tel
        
    ) {

        String newPw = utilHelper.randomPassword(10);
        Member input = new Member();
        input.setUserName(userName);
        input.setUserId(userId);
        input.setTel(tel);
        input.setUserPw(newPw);

        try {
            memberService.resetPw(input);
        } catch (Exception e) {
            webHelper.serverError(e);
            return null;
        }

        model.addAttribute("item", input);
        model.addAttribute("password", newPw);

        return "member/resetPwIn";
    }
}
