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

    @GetMapping("/myPage/injury")
    public String injury(Model model) {
        model.addAttribute("username", "사용자 이름");
        model.addAttribute("grade", "등급");
        model.addAttribute("reward", "리워드");

        model.addAttribute("shoppingHistoryCount", 0); 
        model.addAttribute("couponCount", 0);           
        model.addAttribute("reviewCount", 0);
        return "myPage/injury";
    }

}
