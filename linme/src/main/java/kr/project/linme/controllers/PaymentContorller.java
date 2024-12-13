package kr.project.linme.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpServletRequest;
import kr.project.linme.helpers.FileHelper;
import kr.project.linme.helpers.WebHelper;
import kr.project.linme.models.Cart;
import kr.project.linme.models.Member;
import kr.project.linme.models.Payment;
import kr.project.linme.services.CartService;
import kr.project.linme.services.PaymentService;


@Controller
public class PaymentContorller {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CartService cartService;

    @Autowired
    private WebHelper webHelper;

    @Autowired
    private FileHelper fileHelper;

    @SuppressWarnings("null")
    @PostMapping("/payment/payment")
    public String paymentAdd(
        Model model,
        HttpServletRequest request,
        @SessionAttribute("memberInfo") Member memberInfo,
        @RequestParam("cart_id") List<Integer> cartIdTmp
    ) {
        // 장바구니 항목을 저장할 리스트 생성
        List<Cart> carts = new ArrayList<>();

        // 총 가격을 저장할 변수 초기화
        int totalPrice = 0;

        // 장바구니 항목 ID 리스트를 순회
        for (int i=0; i<cartIdTmp.size(); i++) {
            // 새로운 Cart 객체 생성 및 설정
            Cart c = new Cart();
            c.setCartId(cartIdTmp.get(i));
            c.setMemberId(memberInfo.getMemberId());

            Cart cart = null;
            
            try {
                // 장바구니 항목을 서비스에서 가져옴
                cart = cartService.getItem(c);
            } catch (Exception e) {
                webHelper.serverError(e);
                return null;
            }

            // 이미지 URL 설정
            cart.setImg(fileHelper.getUrl(cart.getImg()));

            // 항목의 총 가격 계산
            int t = cart.getProductCount() * cart.getSalePrice();
            totalPrice += t;

            // 장바구니 리스트에 항목 추가
            carts.add(cart);
        }

        // 결제 정보를 저장할 Payment 객체 생성 및 설정
        Payment input = new Payment();
        input.setOrderName(memberInfo.getUserName());
        input.setOrderTel(memberInfo.getTel());
        input.setAddr1(memberInfo.getAddr1());
        input.setAddr2(memberInfo.getAddr2());
        input.setNickname(memberInfo.getNickname());
        input.setAddrMsg(memberInfo.getAddrMsg());        

        Payment payment = null;

        try {
            // 주문/결제 정보를 서비스에 추가
            payment = paymentService.addItem(input);
        } catch (Exception e) {
            webHelper.serverError(e);
            return null;
        }
        
        // 모델에 결제 정보와 장바구니 항목 추가
        model.addAttribute("payment", payment);
        model.addAttribute("items", carts);

        // 결제 페이지로 이동
        return "payment/payment";
    }
}
