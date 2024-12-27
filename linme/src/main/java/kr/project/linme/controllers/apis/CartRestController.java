package kr.project.linme.controllers.apis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import kr.project.linme.helpers.RestHelper;
import kr.project.linme.models.Cart;
import kr.project.linme.models.Member;
import kr.project.linme.services.CartService;


@RestController
@Tag(name = "Cart API", description = "장바구니 관련 API")
public class CartRestController {

    @Autowired
    private CartService cartService;

    @Autowired
    private RestHelper restHelper;

    /** 
     * 상세상품에서 장바구니 추가 
     * @param request - HTTP 요청 객체
     * @param memberInfo - 세션에 저장된 회원 정보
     * @param memberId - 회원 ID
     * @param productId - 상품 ID
     * @param productCount - 상품 수량
     * @return JSON 형식의 응답 데이터
     */
    @PostMapping("/api/cart/cart/add")
    @Operation(summary = "장바구니 추가", description = "상세상품에서 장바구니에 상품을 추가합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "장바구니에 상품 추가 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "400", description = "장바구니에 상품 추가 실패", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)))
    })
    @Parameters({
        @Parameter(name = "memberId", required = true, description = "회원 ID", schema = @Schema(type = "integer")),
        @Parameter(name = "productId", required = true, description = "상품 ID", schema = @Schema(type = "integer")),
        @Parameter(name = "productCount", required = true, description = "상품 수량", schema = @Schema(type = "integer"))
    })
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
     * 장바구니 수량 변경경
     * @param cartIdTmp - 장바구니 ID (문자열 형태)
     * @param productCountTmp - 변경경할 상품 수량 (문자열 형태)
     * @param memberInfo - 세션에 저장된 회원 정보
     * @return JSON 형식의 응답 데이터
     */
    @PutMapping("/api/cart/cart/edit")
    @Operation(summary = "장바구니 수량 변경", description = "장바구니에 담긴 상품의 수량을 변경합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "장바구니 상품 수량 변경 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "400", description = "장바구니 상품 수량 변경 실패", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)))
    })
    @Parameters({
        @Parameter(name = "cartIdTmp", required = true, description = "장바구니 ID", schema = @Schema(type = "string")),
        @Parameter(name = "productCountTmp", required = true, description = "변경할 상품 수량", schema = @Schema(type = "string"))
    })
    public Map<String, Object> cartEdit(
            @RequestParam("cartIdTmp") String cartIdTmp,
            @RequestParam("productCountTmp") String productCountTmp,
            @SessionAttribute("memberInfo") Member memberInfo) {

        // 문자열 형태의 장바구니 ID를 정수형으로 변환
        int cartId = Integer.parseInt(cartIdTmp);
        // 문자열 형태의 상품 수량을 정수형으로 변환
        int productCount = Integer.parseInt(productCountTmp);

        // Cart 객체 생성 및 값 설정
        Cart input = new Cart();
        input.setCartId(cartId); // 장바구니 ID 설정
        input.setProductCount(productCount); // 상품 수량 설정
        input.setMemberId(memberInfo.getMemberId()); // 회원 ID 설정

        Cart output = null;

        try {
            // 장바구니 아이템 수정 서비스 호출
            output = cartService.editItem(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        // 응답 데이터 생성
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("cart", output);

        // JSON 형식의 응답 데이터 반환
        return restHelper.sendJson(data);
    }

    /**
     * 장바구니 리스트 삭제
     * @param cartIdTmp - 삭제할 장바구니 ID 리스트
     * @param memberInfo - 세션에 저장된 회원 정보
     * @return JSON 형식의 응답 데이터
     */
    @DeleteMapping("/api/cart/cart/deleteList")
    @Operation(summary = "장바구니 선택 삭제", description = "장바구니에 담긴 상품을 삭제합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "장바구니 상품 삭제 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "400", description = "장바구니 상품 삭제 실패", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
    })
    @Parameters({
        @Parameter(name = "cartIdTmp", required = true, description = "삭제할 장바구니 ID 리스트", schema = @Schema(type = "array", implementation = Integer.class))
    })
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

        // 성공적으로 삭제된 경우 JSON 응답 반환
        return restHelper.sendJson();
    }
}
