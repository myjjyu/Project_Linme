package kr.project.linme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PaymentContorller {
    @GetMapping("/payment/payment")
    public String payment(Model model) {
        model.addAttribute("addressNickname", "주소 닉네임");
        model.addAttribute("addressName", "회원이름");
        model.addAttribute("phone", "회원 전화번호");
        model.addAttribute("address", "회원 주소");
        
        return "payment/payment";
    }
}
