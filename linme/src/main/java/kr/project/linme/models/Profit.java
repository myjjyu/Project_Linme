package kr.project.linme.models;

import lombok.Data;

@Data
public class Profit {

    private int profitId;           // 수익 ID
    private String categoryId;     // 카테고리 일련번호 
    private int totalCount;          // 카테고리 총 판매량
    private String regDate;          // 판매 날짜
    
}


