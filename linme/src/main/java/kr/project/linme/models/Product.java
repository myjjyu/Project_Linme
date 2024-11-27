package kr.project.linme.models;

import java.awt.Image;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Product {
  private int productId;         // 상품 일련번호 
  private String productName;    // 상품명 
  private int brandId;           // 브랜드 일련번호 
  private int price;             // 정가 
  private int salePrice;         // 판매가 
  private int discountRate;      // 할인율 
  private String productImg;     // 상품 이미지 
  private String detailImg;      // 상세 이미지 
  private int categoryId;        // 카테고리 ID 
  private String headerItem;     // 신상품, 베스트, 특가상품 
  private int discountPrice;     // 할인 금액 
  private String regDate;        // 등록일시 
  private String editDate;       // 변경일시

  List<Image> imageList; // 나중에 이미지 추가>!


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