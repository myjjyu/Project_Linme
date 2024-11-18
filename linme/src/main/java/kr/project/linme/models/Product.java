package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Product {
  private int id;                 // 상품 일련번호 
  private String product_name;    // 상품명 
  private int brand_id;           // 브랜드 일련번호 
  private int price;              // 정가 
  private int sale_price;         // 판매가 
  private int discount_rate;      // 할인율 
  private String product_img;     // 상품 이미지 
  private String detail_img;      // 상세 이미지 
  private int category_id;        // 카테고리 ID 
  private String header_item;     // 신상품, 베스트, 특가상품 
  private int discount_price;     // 할인 금액 
  private String reg_date;        // 등록일시 
  private String edit_date;       // 변경일시

  @Getter
  @Setter
  private static int listCount = 0;

  @Getter
  @Setter
  private static int offset = 0;
}