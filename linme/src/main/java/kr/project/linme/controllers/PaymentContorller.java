package kr.project.linme.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.project.linme.helpers.FileHelper;
import kr.project.linme.helpers.WebHelper;
import kr.project.linme.models.Cart;
import kr.project.linme.models.Member;
import kr.project.linme.models.OrderItem;
import kr.project.linme.models.Payment;
import kr.project.linme.models.Product;
import kr.project.linme.services.CartService;
import kr.project.linme.services.OrderItemService;
import kr.project.linme.services.PaymentService;
import kr.project.linme.services.ProductService;


@Controller
public class PaymentContorller {
    @Autowired
    private ProductService productService;

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
    @PostMapping("/payment/payment")
    public String paymentAdd(
        Model model,
        HttpServletRequest request,
        @SessionAttribute("memberInfo") Member memberInfo,
        @RequestParam(value = "cart_id", required = true) List<Integer> cartIdTmp
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

            // 디버깅을 위해 로그 추가
            System.out.println("memberId: " + memberInfo.getMemberId());

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
            // 주문/결제 정보를 서비스에 추가
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
        model.addAttribute("memberInfo", memberInfo); // memberInfo 추가

        // 결제 페이지로 이동
        return "payment/payment";
    }

    /** 상세 상품에서 주문/결제 폼 */
    @PostMapping("/payment/payment_by_detail")
    public String paymentByDetail(
        Model model,
        HttpServletRequest request,
        @SessionAttribute("memberInfo") Member memberInfo,
        @RequestParam("product_id") int productId,
        @RequestParam("product_count") int productCount
    ) {

        // 장바구니 항목을 저장할 리스트 생성
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setProductCount(productCount);
        cart.setMemberId(memberInfo.getMemberId());

        try {
            // 장바구니 항목을 서비스에서 추가
            cartService.addItem(cart);
        } catch (Exception e) {
            webHelper.serverError(e);
        }
        
        Product product = new Product();

        // 총 가격을 저장할 변수 초기화
        int totalPrice = 0;

        // 상품 정보를 서비스에서 가져옴
        try {
            Product p = new Product();
            p.setProductId(productId);
            product = productService.getItem(p);
        } catch (Exception e) {
            webHelper.serverError(e);
            return null;
        }

        // 이미지 URL 설정
        product.setImg(fileHelper.getUrl(product.getImg()));

        // 총 가격 계산
        totalPrice = product.getSalePrice() * productCount;

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
            // 주문/결제 정보를 서비스에 추가
            payment = paymentService.addItem(input);
        } catch (Exception e) {
            webHelper.serverError(e);
            return null;
        }

        // 세션에 payment_id 저장
        HttpSession session = request.getSession();
        session.setAttribute("paymentId", payment.getPaymentId());

        // 모델에 결제 정보와 상품 정보 추가
        model.addAttribute("payment", input);
        model.addAttribute("product", product);
        model.addAttribute("productCount", productCount);
        model.addAttribute("memberInfo", memberInfo); // memberInfo 추가

        // 결제 페이지로 이동
        return "payment/payment";
    }

    /** 주문 상세 */
    @GetMapping("/payment/order_detail")
    public String orderDetail(
        Model model,
        HttpServletRequest request,
        @SessionAttribute("memberInfo") Member memberInfo,
        @SessionAttribute("paymentId") Integer paymentId,
        @RequestParam("order_no") String orderNo,
        @RequestParam("order_name") String orderName,
        @RequestParam("order_tel") String orderTel,
        @RequestParam("addr1") String addr1,
        @RequestParam("addr2") String addr2,
        @RequestParam("nickname") String nickname,
        @RequestParam("addr_msg") String addrMsg,
        @RequestParam("total_price") int totalPrice,
        @RequestParam("cart_id") List<Integer> cartIds,
        @RequestParam("productCount") List<Integer> productCounts,
        @RequestParam("productId") List<Integer> productIds
    ) {
        
        // 주문 정보를 저장할 Payment 객체 생성 및 설정
        Payment input = new Payment();
        input.setPaymentId(paymentId);
        input.setOrderNo(orderNo);
        input.setOrderName(orderName);
        input.setOrderTel(orderTel);
        input.setAddr1(addr1);
        input.setAddr2(addr2);
        input.setNickname(nickname);
        input.setAddrMsg(addrMsg);
        input.setTotalPrice(totalPrice);
        input.setMemberId(memberInfo.getMemberId());

        // 주문 정보를 모델에 추가
        model.addAttribute("order", input);

        // 주문 상품 정보를 저장할 리스트 생성
        List<Cart> items = new ArrayList<>();

        // 주문 상품 정보를 생성
        for (int i=0; i<cartIds.size(); i++) {
            Cart item = new Cart();
            item.setCartId(cartIds.get(i));
            // item.setProductCount(productCounts.get(i));
            // item.setProductId(productIds.get(i));
            item.setMemberId(memberInfo.getMemberId());

            Cart cart = null;

            try {
                // 주문 상품 정보를 서비스에서 가져옴
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
        model.addAttribute("paymentId", paymentId);
        model.addAttribute("items", items);

        // 주문 상세 페이지로 이동
        return "payment/order_detail";
    }
    
    /** 주문 완료 및 내역 */
    @PostMapping("/mypage/shopping/order")
    public String orderOk(
        Model model,
        @SessionAttribute("memberInfo") Member memberInfo,
        @RequestParam("payment_id") int paymentId,
        @RequestParam("order_no") String orderNo,
        @RequestParam("order_name") String orderName,
        @RequestParam("order_tel") String orderTel,
        @RequestParam("addr1") String Addr1,
        @RequestParam("addr2") String Addr2,
        @RequestParam("nickname") String Nickname,
        @RequestParam("addr_msg") String addrMsg,
        @RequestParam("total_price") Integer totalPrice,
        @RequestParam("cart_id") List<Integer> cartIdTmp
    ) {

        // 주문/결제 객체 생성 및 설정
        Payment input = new Payment();
        input.setPaymentId(paymentId);
        input.setOrderNo(orderNo);
        input.setMemberId(memberInfo.getMemberId());
        // input.setOrderStatus("결제완료");
        input.setOrderName(memberInfo.getUserName());
        input.setOrderTel(memberInfo.getTel());
        input.setAddr1(memberInfo.getAddr1());
        input.setAddr2(memberInfo.getAddr2());
        input.setNickname(memberInfo.getNickname());
        input.setAddrMsg(memberInfo.getAddrMsg());
        input.setTotalPrice(totalPrice);

        // 주문/결제 정보 저장
        Payment payment = null;

        try {
            // 주문/결제 정보 저장
            payment = paymentService.editItem(input);
        } catch (Exception e) {
            webHelper.serverError(e);
            return null;
        } 
        
        // 주문 항목을 저장할 리스트 생성
        List<Cart> carts = new ArrayList<>();

        // 장바구니 항목을 가져옴
        for (int i=0; i<cartIdTmp.size(); i++) {
            Cart c = new Cart();
            c.setCartId(cartIdTmp.get(i)); // 장바구니 ID 설정
            c.setMemberId(memberInfo.getMemberId()); // 회원 ID 설정

            // 장바구니 항목을 저장할 변수
            Cart cart = null;
            
            try {
                // 장바구니 항목을 가져옴
                cart = cartService.getItem(c);
            } catch (Exception e) {
                webHelper.serverError(e);
                return null;
            }

            // 장바구니 목록에 추가
            carts.add(cart);
        }

        // 주문 상품 항목을 저장할 리스트 생성
        List<OrderItem> items = new ArrayList<>();

        // 주문 상품 항목을 생성
        for (int i=0; i<carts.size(); i++) {
            OrderItem orderItem = new OrderItem();

            Cart c = carts.get(i);
            // orderItem.setPaymentId(paymentId); // 주문/결제 ID 설정
            // orderItem.setProductId(c.getProductId()); // 상품 ID 설정
            orderItem.setOrderBname(c.getBrandName()); // 브랜드 이름 설정
            orderItem.setOrderPname(c.getProductName()); // 상품 이름 설정
            orderItem.setOrderCount(c.getProductCount()); // 주문 수량 설정
            orderItem.setOrderSprice(c.getSalePrice() * c.getProductCount()); // 주문 가격 계산
            orderItem.setOrderImg(c.getImg()); // 상품 이미지 설정

            // 주문 상품 항목 저장
            OrderItem item = null;

            try {
                // 주문 상품 항목 저장
                item = orderItemService.addItem(orderItem);
            } catch (Exception e) {
                webHelper.serverError(e);
                return null;
            }

            // item.setPaymentId(paymentId); 

            // 주문 상품 항목 목록에 추가
            items.add(item);
        }

        // 장바구니 항목 삭제
        for (int i=0; i<carts.size(); i++) {
            Cart c = new Cart();
            c.setCartId(cartIdTmp.get(i)); // 장바구니 ID 설정
            c.setMemberId(memberInfo.getMemberId()); // 회원 ID 설정

            try {
                // 장바구니 항목 삭제
                cartService.deleteItem(c);
            } catch (Exception e) {
                webHelper.serverError(e);
                return null;
            }
        }        

        // 모델에 결제 정보와 주문 항목 추가
        model.addAttribute("payment", payment);
        model.addAttribute("orderItems", items);

        // 주문 완료 페이지로 이동
        return "mypage/shopping/order";
    }
}
