package kr.project.linme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.project.linme.helpers.FileHelper;
import kr.project.linme.helpers.WebHelper;
import kr.project.linme.models.Cart;
import kr.project.linme.services.CartService;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private WebHelper webHelper;

    @Autowired
    private FileHelper fileHelper;

    @GetMapping("/cart/cart")
    public String cart(Model model,
        @RequestParam(value = "memberId", defaultValue = "1") int memberId
    ) {
        Cart cart = new Cart();
        cart.setMemberId(memberId);

        List<Cart> output = null;

        try {
            output = cartService.getList(cart);

            for ( Cart item : output) {
                item.setImg(fileHelper.getUrl(item.getImg()));
            }
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("cart", output);

        return "cart/cart";
    }

    // @GetMapping("/cart/cart")
    // public String cart(Model model,
    //     @RequestParam(value = "memberId", defaultValue = "1") int memberId
    // ) {
    //     Cart cart = new Cart();
    //     cart.setMemberId(memberId);

    //     List<Cart> output = null;

    //     try {
    //         output = cartService.getList(cart);
    //     } catch (Exception e) {
    //         webHelper.serverError(e);
    //     }

    //     if (output != null && !output.isEmpty()) {
    //         model.addAttribute("cart", output.get(0)); // 첫 번째 Cart 객체를 모델에 추가
    //     } else {
    //         model.addAttribute("cart", cart); // 빈 Cart 객체를 모델에 추가
    //     }

    //     return "cart/cart";
    // }
    
}
