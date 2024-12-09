package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Product {
  private int productId; // 상품 일련번호
  private String productName; // 상품명
  private int brandId; // 브랜드 일련번호
  private int price; // 정가
  private int salePrice; // 판매가
  private int discountRate; // 할인율
  private int categoryId; // 카테고리 ID
  private int discountPrice; // 할인 금액
  private String regDate; // 등록일시
  private String editDate; // 변경일시

  // 이미지테이블
  private String img; // 이미지
  private String dImg; // 상세이미지

  // 브랜드테이블
  private String brandName; // 브랜드명

  // 카트테이블
  private int productCount; // 상품 총 수량

  // 멤버테이블
  private int memberId; // 회원 일련번호

  // salePrice 설정 시 discountPrice 자동 계산
  public void setSalePrice(int salePrice) {
    this.salePrice = salePrice;
    this.discountPrice = this.price - salePrice; // 할인 금액 계산
  }

  @Getter
  @Setter
  private static int listCount = 0;

  @Getter
  @Setter
  private static int offset = 0;

}