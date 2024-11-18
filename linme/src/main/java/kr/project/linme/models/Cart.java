package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Cart {
    private int id;             // 장바구니 일련번호
    private int productCount;   // 선택한 상품의 수량
    private int totalPrice;     // 전체 주문 금액
    private int memberId;       // 회원 일련번호
    private int productId;      // 상품 일련번호
    private String regDate;     // 등록 일시
    private String editDate;    // 변경 일시

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}