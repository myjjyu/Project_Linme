package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.Member;
public interface Member2Service {
    

    //회원가입
    public Member addItem(Member input) throws Exception;
    
    //로그인
    public Member login(Member input)throws Exception;

    //비밀번호변경
    public Member editPw(Member input)throws Exception;

    //비밀번호 일치 여부 확인
    public boolean checkPassword(Member input) throws Exception;

    //닉네임 중복 검사
    public void isUniqueNickname(String nickname) throws Exception;

    // 프로필 사진 변경
    public Member updateProfile(Member input) throws Exception;

    // 닉네임 변경
    public Member updateNickname(Member input) throws Exception;

    // 탈퇴회원 관리
    public List<Member> processOutMembers() throws Exception;
    
}

