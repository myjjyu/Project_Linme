package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Cart {
    private int cartId;             // 장바구니 일련번호
    private Integer productCount;   // 선택한 상품의 수량 (Null값 허용)
    private Integer totalPrice;     // 전체 주문 금액 (Null값 허용)
    private Integer memberId;       // 회원 일련번호 (참조키)
    private Integer productId;      // 상품 일련번호 (참조키)
    private String regDate;         // 등록 일시
    private String editDate;        // 변경 일시

    /** 상품 */
    private String productName;     // 상품 이름
    private int salePrice;          // 판매 가격
    private int price;              // 정가

    /** 브랜드 */
    private String brandName;       // 브랜드 이름

    /** 이미지 */
    private String img;             // 이미지

    /** 추가 */
    private int sumTotalPrice;      // 전체 주문 금액 합계

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}