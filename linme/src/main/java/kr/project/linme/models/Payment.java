package kr.project.linme.models;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Payment {
    private int paymentId;      // 주문/결제 일련번호
    private int memberId;       // 회원 일련번호
    private String orderNo;     // 주문 번호
    private String orderName;   // 주문자 이름
    private String orderTel;    // 주문자 연락처
    private String addr1;       // 검색된 주소
    private String addr2;       // 나머지 주소
    private String nickname;    // 배송지명(닉네임임)
    private String addrMsg;     // 배송 요청사항
    private int discountPrice;  // 할인 금액
    private int totalPrice;     // 전체 주문금액
    private String regDate;     // 등록일시
    private String editDate;    // 변경일시
    
    // orderItem 주문 상품 확인 정보
    private int orderItemId;    // 주문 상품 확인 일련번호
    private String orderBname;  // 주문 상품 브랜드명
    private String orderPname;  // 주문 상품 상품명
    private int orderCount;     // 주문 상품 확인 수량
    private int orderSprice;    // 주문 상품 판매가
    private String orderImg;    // 주문 상품 이미지

    private List<OrderItem> orderItems; // 주문 상품 확인 정보를 담을 객체

    @Getter
    @Setter
    private static int count;

    public static void orderCountReset() {
        count = 0;
    }

    public static void orderCount() {
        count++;
    }
    
    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;

}

    
