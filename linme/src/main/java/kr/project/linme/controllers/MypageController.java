package kr.project.linme.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import kr.project.linme.models.Member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@Tag(name = "MyPage API", description = "마이페이지지 API")
public class MypageController {

    @GetMapping("/myPage")
    public String myPage(HttpSession session, Model model) {
        Member memberInfo = (Member) session.getAttribute("memberinfo");
        if (memberInfo != null) {
            // 로그인하지 않은 경우 처리
            return "redirect:/login";
        }
        model.addAttribute("memberinfo", memberInfo);
        return "myPage/myPage";
    }
    
    @GetMapping("/myPage/profile")
    public String getProfilePage(HttpSession session, Model model) {
        Member memberInfo = (Member) session.getAttribute("memberInfo");

        if (memberInfo == null) {
            memberInfo = new Member();
            memberInfo.setProfile("/assets/img/myPage/profileimg.jpg"); // 기본 프로필 이미지 경로 설정
            session.setAttribute("memberInfo", memberInfo);
        } else if (memberInfo.getProfile() == null || memberInfo.getProfile().isEmpty()) {
            memberInfo.setProfile("/assets/img/myPage/profileimg.jpg"); // 기본값 설정
        }

        model.addAttribute("memberInfo", memberInfo);
        return "myPage/profile";
    }


    @GetMapping("/myPage/level")
    public String level() {
        return "myPage/level";
    }

    @GetMapping("/myPage/reward")
    public String reward() {
        return "myPage/reward";
    }

    @GetMapping("/myPage/reward/history")
    public String history() {
        return "myPage/reward/history";
    }
    @GetMapping("/myPage/myinfo")
    public String myinfo(HttpSession session, Model model) {

        Member memberInfo = (Member) session.getAttribute("memberinfo");
        if (memberInfo != null) {
            // 로그인하지 않은 경우 처리
            return "redirect:/login";
        }
        model.addAttribute("memberinfo", memberInfo);
        return "myPage/myinfo";
    }

    @GetMapping("/myPage/myinfo/modify")
    public String modify() {
        return "/myPage/myinfo/modify";
    }

    @GetMapping("/myPage/myinfo/passwordModify")
    public String passwordModify() {
        return "/myPage/myinfo/passwordModify";
    }

    @GetMapping("/myPage/myinfo/notification")
    public String notification() {
        return "/myPage/myinfo/notification";
    }

    @GetMapping("/myPage/myinfo/withdrawal")
    public String withdrawal() {
        return "/myPage/myinfo/withdrawal";
    }


    @GetMapping("/myPage/productInquiry")
    public String productInquiry() {
        return "myPage/productInquiry";
    }

    @GetMapping("/myPage/refundAccount")
    public String refundAccount() {
        return "myPage/refundAccount";
    }

    @GetMapping("/myPage/addressBook")
    public String addressBook() {
        return "myPage/addressBook";
    }

    @GetMapping("/myPage/addressBookAdd")
    public String addressBookAdd() {
        return "myPage/addressBookAdd";
    }

    @GetMapping("/myPage/notice")
    public String notice() {
        return "myPage/notice";
    }

    @GetMapping("/myPage/faq")
    public String faq() {
        return "myPage/faq";
    }

    @GetMapping("/myPage/inquiry")
    public String injury() {
        return "myPage/inquiry";
    }

    
}
