package kr.project.linme.controllers.apis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpServletRequest;
import kr.project.linme.helpers.RestHelper;
import kr.project.linme.models.Cart;
import kr.project.linme.models.Member;
import kr.project.linme.services.CartService;


@RestController
public class CartRestController {

    @Autowired
    private CartService cartService;

    @Autowired
    private RestHelper restHelper;

    /** 상세상품에서 장바구니 추가 */
    @PostMapping("/api/cart/cart/add")
    public Map<String, Object> cartAdd(
        HttpServletRequest request,
        @SessionAttribute("memberInfo") Member memberInfo,
        @RequestParam("memberId") int memberId,
        @RequestParam("productId") int productId,
        @RequestParam("productCount") int productCount
    ) {

        // Cart 객체 생성 및 요청 파라미터 설정
        Cart input = new Cart();
        input.setMemberId(memberId);
        input.setProductId(productId);
        input.setProductCount(productCount);

        // 결과를 저장할 Cart 객체
        Cart output = null;

        try {
            // 장바구니에 동일한 상품이 있는지 확인
            int count = cartService.getCount(input);
            if (count > 0) {
                // 동일한 상품이 있다면 수량만 변경
                output = cartService.editUniqueCart(input);
            } else {
                // 동일한 상품이 없다면, 장바구니에 추가
                output = cartService.addItem(input);
            }
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        // 응답 데이터 맵 생성
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("cart", output); // 추가된 장바구니 아이템 정보
        data.put("redirectUrl", "/cart/cart"); // 리디렉션 URL

        // JSON 형식으로 응답 반환
        return restHelper.sendJson(data);
    }



    /**
     * 장바구니 수량(개수) 표시
     */
    @GetMapping("/api/cart/count")
    public Map<String, Object> cartCount(
            @RequestParam("memberId") int memberId) {
        Cart input = new Cart();
        input.setMemberId(memberId);

        int cartCount = 0;

        try {
            cartCount = cartService.getCount(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("cartCount", cartCount);

        return restHelper.sendJson(data);
    }

    @GetMapping("/api/cart/unique_count")
    public Map<String, Object> uniqueCart(
            HttpServletRequest request,
            @SessionAttribute("memberInfo") Member memberInfo,
            @RequestParam("productId") Integer productId) {

        Cart input = new Cart();
        input.setMemberId(memberInfo.getMemberId());
        input.setProductId(productId);

        Cart uniqueCart = null;

        try {
            uniqueCart = cartService.uniqueCartCount(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("uniqueCart", uniqueCart);

        return restHelper.sendJson(data);
    }

    @PutMapping("/api/cart/unique_edit")
    public Map<String, Object> uniqueCartEdit(
            @RequestParam("productCount") String productCount,
            @RequestParam("cartId") String cartIdTmp,
            @SessionAttribute("memberInfo") Member memberInfo) {
        int productCountTmp = Integer.parseInt(productCount);
        int cartId = Integer.parseInt(cartIdTmp);

        Cart input = new Cart();
        input.setCartId(cartId);
        input.setProductCount(productCountTmp);
        input.setMemberId(memberInfo.getMemberId());

        try {
            cartService.editUniqueCart(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        return restHelper.sendJson();
    }

    /**
     * 장바구니 수량 수정
     * 
     * @param quantity   - 장바구니 수량
     * @param cartId   - 장바구니 일련번호
     * @param memberInfo - 회원 번호
     * @return
     */
    @PutMapping("/api/cart/cart/edit")
    public Map<String, Object> cartEdit(
            @RequestParam("cartIdTmp") String cartIdTmp,
            @RequestParam("productCountTmp") String productCountTmp,
            @SessionAttribute("memberInfo") Member memberInfo) {

        int cartId = Integer.parseInt(cartIdTmp);
        int productCount = Integer.parseInt(productCountTmp);

        Cart input = new Cart();
        input.setCartId(cartId);
        input.setProductCount(productCount);
        input.setMemberId(memberInfo.getMemberId());

        Cart output = null;

        try {
            output = cartService.editItem(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("cart", output);

        return restHelper.sendJson(data);
    }

    /**
     * 장바구니 삭제
     * 
     * @param cartId   - 장바구니 번호
     * @param memberInfo - 회원 번호
     * @return
     */
    @DeleteMapping("/api/cart/cart/delete")
    public Map<String, Object> cartDelete(
            @RequestParam("cartIdTmp") String cartIdTmp,
            @SessionAttribute("memberInfo") Member memberInfo) {

        int cartId = Integer.parseInt(cartIdTmp);

        Cart input = new Cart();
        input.setCartId(cartId);
        input.setMemberId(memberInfo.getMemberId());

        try {
            cartService.deleteItem(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        return restHelper.sendJson();
    }

    /**
     * 장바구니 리스트 삭제
     */
    @DeleteMapping("/api/cart/cart/deleteList")
    public Map<String, Object> cartListDelete(
            @RequestParam("cartIdTmp") List<Integer> cartIdTmp,
            @SessionAttribute("memberInfo") Member memberInfo) {

        // 장바구니 번호를 저장하기 위한 객체
        List<Cart> output = new ArrayList<>();

        // 장바구니 번호를 하나씩 꺼내서 처리
        for (int i = 0; i < cartIdTmp.size(); i++) {
            Cart input = new Cart();
            input.setCartId(cartIdTmp.get(i)); // 장바구니 번호
            input.setMemberId(memberInfo.getMemberId()); // 회원 번호

            // 장바구니 번호를 삭제하기 위한 처리
            output.add(input);
            try {
                // 장바구니 번호를 삭제
                cartService.deleteItem(input);
            } catch (Exception e) {
                return restHelper.serverError(e);
            }
        }

        return restHelper.sendJson();
    }

    
}
