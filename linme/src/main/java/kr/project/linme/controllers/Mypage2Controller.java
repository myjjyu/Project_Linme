package kr.project.linme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Mypage2Controller {
    @GetMapping("/myPage/coupon")
    public String coupon(Model model) {
        return "myPage/coupon";
    }
}
