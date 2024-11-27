package kr.project.linme.services;

import kr.project.linme.models.Member;

public interface MemberService {

    public Member addItem(Member input) throws Exception;

    // user_id / nickname 중복 검사
    public void isUniqueUserId(String userId) throws Exception;

    public void isUniqueNickname(String nickname) throws Exception;

}
