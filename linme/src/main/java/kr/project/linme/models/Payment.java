package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Payment {
    private int payment_id; // 주문/결제 일련번호
    private String order_name; // 주문자 이름
    private String order_tel; // 주문자 연락처
    private String addr1; // 검색된 주소
    private String addr2; // 나머지 주소
    private String addr_name; // 배송지명
    private String addr_msg; // 배송 요청사항
    private int discount_price; // 할인 금액
    private int total_price; // 전체 주문금액
    private String reg_date; // 등록일시
    private String edit_date; // 변경일시
    
    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;

}

    
