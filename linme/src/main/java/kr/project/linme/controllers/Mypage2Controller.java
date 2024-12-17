package kr.project.linme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/mypage/shopping/order", method = {RequestMethod.GET, RequestMethod.POST})
    public String order(Model model) {
        return "mypage/shopping/order";
    }

    @GetMapping("/mypage/shopping/claim")
    public String claim(Model model) {
        return "mypage/shopping/claim";
    }
}
