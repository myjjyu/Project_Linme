package kr.project.linme.controllers.apis;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpServletRequest;
import kr.project.linme.helpers.RestHelper;
import kr.project.linme.models.Cart;
import kr.project.linme.models.Member;
import kr.project.linme.services.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*") // CORS 설정
@RestController
public class CartRestController {

    @Autowired
    private CartService cartService;

    @Autowired
    private RestHelper restHelper;

    /** 상세상품에서 장바구니로 POST */
    @PostMapping("/api/cart/cart/add")
    public Map<String, Object> postMethodName(
        HttpServletRequest request,
        @SessionAttribute("memberInfo") Member memberInfo,
        @RequestParam("memberId") int memberId,
        @RequestParam("productId") int productId,
        @RequestParam("productCount") int productCount
    ) {

        Cart input = new Cart();
        input.setMemberId(memberId);
        input.setProductId(productId);
        input.setProductCount(productCount);

        Cart output = null;

        try {
            output = cartService.addItem(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("cart", output);

        return restHelper.sendJson(data);
    }
    

    /**
     * Cart 수량 변경
     * @param cartId - 수정할 Cart
     * @param productCount - 수정할 제품 수량
     * @return 수정된 Cart 객체를 포함한 JSON 데이터
     */
    @PutMapping("/api/cart/cart/edit")
    public Map<String,Object> editCart ( 
        @RequestParam("cartId") int cartId,
        @RequestParam("productCount") int productCount,
        @RequestParam("memberId") int memberId
     ) {
        Cart input = new Cart();
        input.setCartId(cartId);
        input.setProductCount(productCount);
        input.setMemberId(memberId);

        // 수정된 Cart 객체를 저장할 변수
        Cart output = null;

        try {
            output = cartService.editItem(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        // JSON 데이터를 저장할 맵 생성
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("cart", output);

        // 콘솔에 출력
        System.out.println(data);

        return restHelper.sendJson(data);
    }

    /**
     * 장바구니에서 상품 삭제
     * @param cartId - 삭제할 Cart
     * @return JSON 데이터
     */
    @DeleteMapping("/api/cart/deleteItem/{cartid}")
    public Map<String,Object> deleteItem ( @PathVariable("cartId") int cartId ) {

        Cart input = new Cart();
        input.setCartId(cartId);

        try {
            cartService.deleteItem(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        return restHelper.sendJson();
    }

    /**
     * 장바구니 상품의 수량 확인
     * @param memberId
     * @param productId
     * @return
     */
    @PutMapping("/api/cart/cart/cartCount/{id}")
    public Map<String, Object> cartCount(
        @RequestParam("memberId") int memberId,
        @RequestParam("productId") int productId){
        
        Cart input = new Cart();
        input.setMemberId(memberId);
        input.setProductId(productId);

        int count = 0;

        try {
            // cartService.selectCount(input);
            // selectCount 메서드가 int를 반환하므로 반환값을 사용하지 않음
            count = cartService.selectCount(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("count", count);

        return restHelper.sendJson(data);
    }
    
    /**
     * 장바구니에서 상품 중복 확인
     * @param memberId - 확인할 Cart
     * @return JSON 데이터
     */
    @PutMapping("/api/cart/cart/uniqueEdit")
    public Map<String, Object> uniqueCartEdit(
        @SessionAttribute("memberInfo") Member memberInfo,
        @RequestParam("productCount") String productCountTmp,
        @RequestParam("cartId") String cartIdTmp
        ) {
            
        int productCount = Integer.parseInt(productCountTmp);
        int cartId = Integer.parseInt(cartIdTmp);

        Cart input = new Cart();
        input.setCartId(cartId);
        input.setProductCount(productCount);
        input.setMemberId(memberInfo.getMemberId());

        try {
            cartService.editUniqueCart(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        return restHelper.sendJson();
    }

    
}
