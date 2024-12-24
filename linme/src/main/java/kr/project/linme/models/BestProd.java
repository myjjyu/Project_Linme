package kr.project.linme.models;

import lombok.Data;

@Data
public class BestProd {

  private int bestProdId; 	   // 인기상품 ID         
  private int orderCount; 	   // 판개 수량            
  private int orderItemId; 	   // 주문된 상품 번호        
  private String productName;  // 상품이름               
  private String regDate; 	   // 데이터 집계가 이루어진 날짜

}