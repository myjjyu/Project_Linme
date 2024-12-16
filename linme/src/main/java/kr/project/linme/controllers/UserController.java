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
        // 새로운 비밀번호 생성
        String newPw = utilHelper.randomPassword(10);

        // 입력된 사용자 정보를 기반으로 Member 객체 생성
        Member input = new Member();
        input.setUserName(userName);
        input.setUserId(userId);
        input.setTel(tel);
        input.setUserPw(newPw);

        try {
            // 비밀번호 재설정 서비스 호출
            memberService.resetPw(input);
        } catch (Exception e) {
            // 서버 오류 처리
            webHelper.serverError(e);
            return null;
        }
        // 모델에 사용자 정보와 새 비밀번호 추가
        model.addAttribute("item", input);
        model.addAttribute("password", newPw);

        // 비밀번호 재설정 완료 페이지로 이동
        return "member/resetPwIn";
    }
}
