package kr.project.linme.models;

import lombok.Data;

@Data
public class NewMember {
    private int newMemberId;  // 신규회원 ID
    private int memberCount;  // 신규회원 수
    private String regDate;   // 데이터 집계 날짜
}
