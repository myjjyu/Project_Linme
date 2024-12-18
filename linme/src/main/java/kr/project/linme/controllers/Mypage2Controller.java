package kr.project.linme.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.project.linme.models.OrderItem;
import kr.project.linme.services.OrderItemService;

@Controller
public class Mypage2Controller {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/mypage/coupon")
    public String coupon(Model model) {
        return "mypage/coupon";
    }

    @GetMapping("/mypage/review")
    public String review(Model model) {
        return "mypage/review";
    }
    
    @GetMapping("/mypage/shopping/claim")
    public String claim(Model model) {
        return "mypage/shopping/claim";
    }
}
