package kr.project.linme.models;

import lombok.Data;

@Data
public class Profit {

    private int ProfitId;           // 수익 ID
    private int categoryId;          // 카테고리 ID    
    private int totalCount;          // 카테고리 총 판매량
    private String regDate;          // 판매 날짜
}


