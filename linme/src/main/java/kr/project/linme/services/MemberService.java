package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.Member;

public interface MemberService {

    public Member addItem(Member input) throws Exception;

    // user_id / nickname 중복 검사
    public void isUniqueUserId(String userId) throws Exception;

    public void isUniqueNickname(String nickname) throws Exception;

    //로그인
    public Member login(Member input)throws Exception;

    // 배송지 변경
    public Member updatePostcode(Member input) throws Exception;

    //비밀번호변경
    public Member editPw(Member input)throws Exception;

    //프로필 정보 변경
    public Member updateProfile(Member input) throws Exception;

    //회원 탈퇴
    public int out(Member input) throws Exception;

    // 탈퇴회원 관리
    public List<Member> processOutMembers() throws Exception;

}
