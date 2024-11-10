package kr.project.linme.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MypageController {

    @GetMapping("/myPage")
    public String myPage(Model model) {
        model.addAttribute("username", "사용자 이름");
        model.addAttribute("grade", "등급");
        model.addAttribute("reward", "리워드");

        model.addAttribute("shoppingHistoryCount", 0); 
        model.addAttribute("couponCount", 0);           
        model.addAttribute("reviewCount", 0);
        return "myPage/myPage";
    }
    @GetMapping("/myPage/profile")
    public String profile() {
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

    @GetMapping("/myPage/productInquiry")
    public String productInquiry(Model model) {
        model.addAttribute("username", "사용자 이름");
        model.addAttribute("grade", "등급");
        model.addAttribute("reward", "리워드");

        model.addAttribute("shoppingHistoryCount", 0); 
        model.addAttribute("couponCount", 0);           
        model.addAttribute("reviewCount", 0);
        return "myPage/productInquiry";
    }

    @GetMapping("/myPage/refundAccount")
    public String refundAccount(Model model) {
        model.addAttribute("username", "사용자 이름");
        model.addAttribute("grade", "등급");
        model.addAttribute("reward", "리워드");

        model.addAttribute("shoppingHistoryCount", 0); 
        model.addAttribute("couponCount", 0);           
        model.addAttribute("reviewCount", 0);
        return "myPage/refundAccount";
    }

    @GetMapping("/myPage/addressBook")
    public String addressBook(Model model) {
        model.addAttribute("username", "사용자 이름");
        model.addAttribute("grade", "등급");
        model.addAttribute("reward", "리워드");

        model.addAttribute("shoppingHistoryCount", 0); 
        model.addAttribute("couponCount", 0);           
        model.addAttribute("reviewCount", 0);
        return "myPage/addressBook";
    }

    @GetMapping("/myPage/addressBookAdd")
    public String addressBookAdd(Model model) {
        model.addAttribute("username", "사용자 이름");
        model.addAttribute("grade", "등급");
        model.addAttribute("reward", "리워드");

        model.addAttribute("shoppingHistoryCount", 0); 
        model.addAttribute("couponCount", 0);           
        model.addAttribute("reviewCount", 0);
        return "myPage/addressBookAdd";
    }

    @GetMapping("/myPage/notice")
    public String notice(Model model) {
        model.addAttribute("username", "사용자 이름");
        model.addAttribute("grade", "등급");
        model.addAttribute("reward", "리워드");

        model.addAttribute("shoppingHistoryCount", 0); 
        model.addAttribute("couponCount", 0);           
        model.addAttribute("reviewCount", 0);
        return "myPage/notice";
    }

    @GetMapping("/myPage/faq")
    public String faq(Model model) {
        model.addAttribute("username", "사용자 이름");
        model.addAttribute("grade", "등급");
        model.addAttribute("reward", "리워드");

        model.addAttribute("shoppingHistoryCount", 0); 
        model.addAttribute("couponCount", 0);           
        model.addAttribute("reviewCount", 0);
        return "myPage/faq";
    }

    @GetMapping("/myPage/inquiry")
    public String injury(Model model) {
        model.addAttribute("username", "사용자 이름");
        model.addAttribute("grade", "등급");
        model.addAttribute("reward", "리워드");

        model.addAttribute("shoppingHistoryCount", 0); 
        model.addAttribute("couponCount", 0);           
        model.addAttribute("reviewCount", 0);
        return "myPage/inquiry";
    }

    
}
