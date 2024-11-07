package kr.project.linme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PaymentContorller {
    @GetMapping("/payment/payment")
    public String payment() {
        return "payment/payment";
    }
    
}
