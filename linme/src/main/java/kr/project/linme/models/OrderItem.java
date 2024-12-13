package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OrderItem {
    private int orderItemId;    // 주문 상품 확인 일련번호
    private int paymentId;      // 주문/결제 일련번호 (참조 키)
    private String orderBname;  // 주문 상품 브랜드명
    private String orderPname;  // 주문 상품 상품명
    private int orderCount;     // 주문 상품 확인 수량
    private int orderSprice;    // 주문 상품 판매가
    private String orderImg;    // 주문 상품 이미지
    private String regDate;     // 등록일시
    private String editDate;    // 변경일시
    
    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
    
}
