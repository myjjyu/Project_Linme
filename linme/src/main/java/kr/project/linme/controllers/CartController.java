package kr.project.linme.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.project.linme.helpers.FileHelper;
import kr.project.linme.helpers.WebHelper;
import kr.project.linme.models.Cart;
import kr.project.linme.models.Member;
import kr.project.linme.services.CartService;

@Controller
@Tag(name = "Cart API", description = "장바구니 API")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private WebHelper webHelper;

    @Autowired
    private FileHelper fileHelper;

    /**
     * 장바구니 첫화면 불러오기
     * 
     * @param memberInfo - 회원 번호
     * @return
     */
    @GetMapping("/cart/cart")
    @Operation(summary = "장바구니 화면 불러오기")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "장바구니 화면 불러오기 성공입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "400", description = "장바구니 화면 불러오기 실패입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)))
    })
    public String orderCart(Model model,
            @SessionAttribute("memberInfo") Member memberInfo) {
                
        Cart input = new Cart();
        input.setMemberId(memberInfo.getMemberId());

        // 주문상품 카운트
        Cart input2 = new Cart();
        input2.setMemberId(memberInfo.getMemberId());

        List<Cart> output = null;

        int count = 0;

        try {
            output = cartService.getList(input);

            count = cartService.getCount(input2);

            // 이미지 경로 설정
            for (Cart item : output) {
                item.setImg(fileHelper.getUrl(item.getImg()));
            }

        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("cart", output);
        model.addAttribute("counts", count);

        return "cart/cart";
    }
}
