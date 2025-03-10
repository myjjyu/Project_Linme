package kr.project.linme.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import jakarta.servlet.http.HttpSession;
import kr.project.linme.helpers.FileHelper;
import kr.project.linme.helpers.WebHelper;
import kr.project.linme.models.Cart;
import kr.project.linme.models.Member;
import kr.project.linme.models.OrderItem;
import kr.project.linme.models.Payment;
import kr.project.linme.services.CartService;
import kr.project.linme.services.OrderItemService;
import kr.project.linme.services.PaymentService;


@Controller
@Tag(name = "Payment API", description = "주문/결제 API")
public class PaymentContorller {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private WebHelper webHelper;

    @Autowired
    private FileHelper fileHelper;

    /** 장바구니에서 주문/결제 폼 */
    @PostMapping("/payment")
    @Operation(summary = "주문/결제 폼", description = "장바구니에서 주문/결제 폼을 불러옵니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "주문/결제 폼 불러오기 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Model.class))),
        @ApiResponse(responseCode = "400", description = "주문/결제 폼 불러오기 실패", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Model.class)))
    })
    @Parameters({
        @Parameter(name = "cart_id", required = true, description = "장바구니 항목 ID 리스트", schema = @Schema(type = "integer")),
    })
    public String paymentAdd(
        Model model,
        HttpServletRequest request,
        @SessionAttribute("memberInfo") Member memberInfo,
        @RequestParam(value = "cart_id", required = true) List<Integer> cartIdTmp
    ) {
        List<Cart> carts = new ArrayList<>();

        int totalPrice = 0;

        // 장바구니 항목 ID 리스트를 순회
        for (int i=0; i<cartIdTmp.size(); i++) {
            // 새로운 Cart 객체 생성 및 설정
            Cart c = new Cart();
            c.setCartId(cartIdTmp.get(i));
            c.setMemberId(memberInfo.getMemberId());

            Cart cart = null;
            
            try {
                // 장바구니 항목 조회
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
        input.setMemberId(memberInfo.getMemberId());
        input.setOrderName(memberInfo.getUserName());
        input.setOrderTel(memberInfo.getTel());
        input.setAddr1(memberInfo.getAddr1());
        input.setAddr2(memberInfo.getAddr2());
        input.setNickname(memberInfo.getNickname());
        input.setAddrMsg(memberInfo.getAddrMsg());
        input.setTotalPrice(totalPrice);

        Payment payment = null;

        try {
            // 주문/결제 정보를 추가
            payment = paymentService.addItem(input);
        } catch (Exception e) {
            webHelper.serverError(e);
            return null;
        }

        // 세션에 payment_id 저장
        HttpSession session = request.getSession();
        session.setAttribute("paymentId", payment.getPaymentId());

        // 모델에 결제 정보와 장바구니 항목 추가
        model.addAttribute("payment", payment);
        model.addAttribute("item", carts);
        model.addAttribute("memberInfo", memberInfo);

        // 결제 페이지로 이동
        return "payment/payment";
    }

    /** 주문 상세 */
    @GetMapping("/payment/order_detail")
    @Operation(summary = "주문 상세", description = "주문 상세 페이지를 불러옵니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "주문 상세 페이지 불러오기 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Model.class))),
        @ApiResponse(responseCode = "400", description = "주문 상세 페이지 불러오기 실패", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Model.class)))
    })
    @Parameters({
        @Parameter(name = "cart_id", required = true, description = "장바구니 항목 ID 리스트", schema = @Schema(type = "integer")),
        @Parameter(name = "productCount", required = true, description = "상품 수량 리스트", schema = @Schema(type = "integer")),
        @Parameter(name = "productId", required = true, description = "상품 ID 리스트", schema = @Schema(type = "integer"))
    })
    public String orderDetail(
        Model model,
        HttpServletRequest request,
        @SessionAttribute("memberInfo") Member memberInfo,
        @SessionAttribute("paymentId") Integer paymentId,
        @RequestParam("cart_id") List<Integer> cartIds,
        @RequestParam("productCount") List<Integer> productCounts,
        @RequestParam("productId") List<Integer> productIds
    ) {
        
        // 주문 정보를 저장할 Payment 객체 생성 및 설정
        Payment input = new Payment();
        input.setPaymentId(paymentId);

        Payment payment = null;

        try {
            // 주문 정보를 가져옴
            payment = paymentService.getItem(input);
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        // 주문 정보를 모델에 추가
        model.addAttribute("order", input);

        // 주문 상품 정보를 저장할 리스트 생성
        List<Cart> items = new ArrayList<>();

        // 주문 상품 정보를 생성
        for (int i=0; i<cartIds.size(); i++) {
            Cart item = new Cart();
            item.setCartId(cartIds.get(i));
            item.setMemberId(memberInfo.getMemberId());

            Cart cart = null;

            try {
                // 주문 상품 정보를 가져옴
                cart = cartService.getItem(item);
            } catch (Exception e) {
                webHelper.serverError(e);
                return null;
            }

            // 이미지 URL 설정
            cart.setImg(fileHelper.getUrl(cart.getImg()));

            // 주문 상품 정보를 리스트에 추가
            items.add(cart);
        }

        // 주문 상품 정보를 모델에 추가
        model.addAttribute("payment", payment);
        model.addAttribute("paymentId", paymentId);
        model.addAttribute("item", items);

        // memberId로 장바구니 항목 가져오기
        List<Cart> cartItems = new ArrayList<>();

        // 장바구니 항목을 가져오기 위한 Cart 객체 생성 및 설정
        for (int i=0; i<cartIds.size(); i++) {
            Cart cartItem = new Cart();
            // memberId, cartId 설정
            cartItem.setMemberId(memberInfo.getMemberId());
            cartItem.setCartId(cartIds.get(i));

            try {
                // 장바구니 항목을 가져옴
                cartItem = cartService.getItem(cartItem);
            } catch (Exception e) {
                webHelper.serverError(e);
            }

            cartItems.add(cartItem);
        }


        // 장바구니 항목을 order_item으로 옮기기
        for (Cart cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPaymentId(paymentId);
            orderItem.setOrderBname(cartItem.getBrandName());
            orderItem.setOrderPname(cartItem.getProductName());
            orderItem.setOrderCount(cartItem.getProductCount());
            orderItem.setOrderSprice(cartItem.getSalePrice());
            orderItem.setOrderImg(cartItem.getImg());
            orderItem.setRegDate(cartItem.getRegDate());
            orderItem.setEditDate(cartItem.getEditDate());

            try {
                // order_item에 추가하고 장바구니에서 삭제
                orderItemService.addItem(orderItem);
                cartService.deleteItem(cartItem);
            } catch (Exception e) {
                webHelper.serverError(e);
            }
        }

        // 주문 완료된 항목을 가져오기 위한 OrderItem 객체 생성 및 설정
        OrderItem orderItem = new OrderItem();
        orderItem.setPaymentId(paymentId);

        // 주문 완료된 항목 리스트 가져오기
        List<OrderItem> orderItems = null;
        try {
            orderItems = orderItemService.getList(orderItem);
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        // 모델에 주문 완료된 항목 추가
        model.addAttribute("orderItems", orderItems);

        // 주문 상세 페이지로 이동
        return "payment/order_detail";
    }

    @GetMapping("/mypage/shopping/order")
    @Operation(summary = "주문 목록", description = "주문 목록 페이지를 불러옵니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "주문 목록 페이지 불러오기 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Model.class))),
        @ApiResponse(responseCode = "400", description = "주문 목록 페이지 불러오기 실패", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Model.class)))
    })
    public String order(Model model,
        @SessionAttribute("memberInfo") Member memberInfo
        ) {
        List<Payment> payments = null;

        // 새로운 Payment 객체를 생성하고 회원 ID를 설정
        Payment paymentInput = new Payment();
        paymentInput.setMemberId(memberInfo.getMemberId()); // memberId 설정
    
        try {
            // 회원 ID를 기준으로 결제 목록을 조회 (order_no를 기준으로 내림차순 정렬)
            payments = paymentService.getList(paymentInput);
    
            // 각 결제 정보에 대해 주문 항목을 조회하고 설정
            for (Payment payment : payments) {
                // 새로운 OrderItem 객체를 생성하고 결제 ID를 설정
                OrderItem orderItemInput = new OrderItem();
                orderItemInput.setPaymentId(payment.getPaymentId()); // paymentId 설정
                // 결제 ID를 기준으로 주문 항목 목록을 조회
                List<OrderItem> items = orderItemService.getList(orderItemInput);

                // 각 주문 항목의 이미지 경로를 설정
                for (OrderItem item : items) {
                    item.setOrderImg(fileHelper.getUrl(item.getOrderImg())); 
                }

                // 결제 정보에 주문 항목 목록을 설정
                payment.setOrderItems(items); // Payment 객체에 orderItems 설정
            }
            
        // 중복 제거
        payments = payments.stream().distinct().collect(Collectors.toList());

        } catch (Exception e) {
            webHelper.serverError(e);
        }
    
        // 모델에 결제 목록을 추가
        model.addAttribute("payments", payments);
    
        // 주문 페이지로 이동
        return "mypage/shopping/order";
    }
}
