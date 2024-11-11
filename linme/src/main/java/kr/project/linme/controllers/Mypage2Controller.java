package kr.project.linme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Mypage2Controller {
    @GetMapping("/mypage/coupon")
    public String coupon(Model model) {
        return "mypage/coupon";
    }

    @GetMapping("/mypage/review")
    public String review(Model model) {
        return "mypage/review";
    }
}
