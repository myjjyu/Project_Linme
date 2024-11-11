package kr.project.linme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CartContorller {
    @GetMapping("/cart/cart")
    public String payment(Model model) {
        return "cart/cart";
    }
    
}
