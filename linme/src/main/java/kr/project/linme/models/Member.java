package kr.project.linme.models;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Member implements Serializable{
    private int memberId;                   // 회원 일련번호
    private String userId;                  // 아이디(이메일)
    private String userPw;                  // 비밀번호
    private String userName;                // 회원 이름
    private String nickname;                // 닉네임
    private String tel;                     // 휴대폰
    private String postcode;                // 우편번호
    private String addr1;                   // 검색된 주소
    private String addr2;                   // 나머지 주소
    private String addrMsg;                 // 배송 요청사항
    private String profile;                 // 프로필 사진
    private String isOut;                   // 탈퇴 여부(Y/N)
    private String isAdmin;                 // 관리자 여부(Y/N)
    private String loginDate;               // 마지막 로그인 일시
    private String regDate;                 // 등록일시
    private String editDate;                // 변경일시

    // 여기 추가 후 재로그인 필요
    private String newUserPw; // 회원정보 수정에서 사용할 신규 비밀번호

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}
