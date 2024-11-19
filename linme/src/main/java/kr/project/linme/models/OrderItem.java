package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OrderItem {
    private int orderItem_id; // 주문 상품 확인 일련번호
    private String order_bname; // 주문 상품 브랜드명
    private String order_pname; // 주문 상품 상품명
    private int order_count; // 주문 상품 확인 수량
    private int order_sprice; // 주문 상품 판매가
    private int order_price; // 주문 상품 구매가
    private int payment_id; // 주문/결제 일련번호 (외래 키)
    private String reg_date; // 등록일시
    private String edit_date; // 변경일시
    
    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
    
}
