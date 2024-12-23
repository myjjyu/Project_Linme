package kr.project.linme.models;

import lombok.Data;

@Data
public class Sales {
    private int salesId;         // 매출 합계 ID
    private int salesTotal;      // 매출 합계
    private String salesDate;    // 매출 날짜
}
