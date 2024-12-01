package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Payment {
    private int paymentId; // 주문/결제 일련번호
    private String orderName; // 주문자 이름
    private String orderTel; // 주문자 연락처
    private String addr1; // 검색된 주소
    private String addr2; // 나머지 주소
    private String addrName; // 배송지명
    private String addrMsg; // 배송 요청사항
    private int discountPrice; // 할인 금액
    private int totalPrice; // 전체 주문금액
    private String regDate; // 등록일시
    private String editDate; // 변경일시
    
    private int memberId; // 회원 일련번호
    private String img; // 이미지
    
    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;

}

    
