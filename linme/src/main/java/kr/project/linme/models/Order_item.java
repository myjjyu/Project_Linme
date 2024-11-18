package kr.project.linme.models;

import lombok.Data;

@Data
public class Order_item {
    private int orderItemId; // 주문 상품 확인 일련번호
    private String orderBname; // 주문 상품 브랜드명
    private String orderPname; // 주문 상품 상품명
    private int orderCount; // 주문 상품 확인 수량
    private int orderSprice; // 주문 상품 판매가
    private int orderPrice; // 주문 상품 구매가
    private int paymentId; // 주문/결제 일련번호 (외래 키)
    private String regDate; // 등록일시
    private String editDate; // 변경일시
}
