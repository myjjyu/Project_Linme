package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BestProd {

  private int bestProdId; 	   // 인기상품 일련번호         
  private int orderCount; 	   // 판개 개수(수량)            
  private int orderItemId; 	   // 주문된 상품 번호        
  private String productName;  // 상품이름               
  private String regDate; 	   // 데이터 집계가 이루어진 날짜

  private String orderDate; // 주문 날짜

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}