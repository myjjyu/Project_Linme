package kr.project.linme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.project.linme.helpers.FileHelper;
import kr.project.linme.helpers.WebHelper;
import kr.project.linme.models.Cart;
import kr.project.linme.models.Member;
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
        // @RequestParam(value = "memberId", defaultValue = "1") int memberId
        @SessionAttribute("memberInfo") Member memberInfo
    ) {
        Cart cart = new Cart();
        // cart.setMemberId(memberId);
        cart.setMemberId(memberInfo.getMemberId());

        List<Cart> output = null;

        try {
            output = cartService.getList(cart);

            for ( Cart item : output) {
                item.setImg(fileHelper.getUrl(item.getImg()));
            }
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        int output2 = 0;
        try {
            output2 = cartService.sumTotalPrice(cart);
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("cart", output);
        model.addAttribute("totalPrice", output2);

        return "cart/cart";
    }

}
