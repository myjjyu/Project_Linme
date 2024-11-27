package kr.project.linme.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.models.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Member2ServiceTest {

    @Autowired
    private Member2Service memberService;

    @Test
    @DisplayName("회원가입 테스트")
    void addMember() throws Exception {
        Member member = new Member();
        member.setUserId("cg2522@naver.com");
        member.setUserPw("123qwe!@#");
        member.setUserName("신지예");
        member.setNickname("지예");
        member.setTel("010-1234-1234");
        member.setPostcode("06035");
        member.setAddr1("서울 강남구 가로수길5");
        member.setAddr2("123호");
        member.setAddrName("신짱구");
        member.setAddrMsg("부재 시 문 앞에 놓아주세요");
        member.setProfile("test.png");

        Member result = null;

        try {
            result = memberService.addItem(member);
        } catch (Exception e) {
            log.error("회원가입 실패", e);
            throw e;
        }

        if (result != null) {
            log.debug("회원가입 성공: " + result.toString());
        }
    }

    @Test
    @DisplayName("닉네임 변경 테스트")
    void updateNickname() throws Exception {
        Member member = new Member();
        member.setMemberId(1); // 테스트 대상 회원 ID
        member.setNickname("yujeong");

        Member result = null;

        try {
            result = memberService.updateNickname(member);
        } catch (Exception e) {
            log.error("닉네임 변경 실패", e);
            throw e;
        }

        if (result != null) {
            log.debug("닉네임 변경 성공: " + result.toString());
        }
    }

    @Test
    @DisplayName("프로필 사진 변경 테스트")
    void updateProfile() throws Exception {
        Member member = new Member();
        member.setMemberId(1); // 테스트 대상 회원 ID
        member.setProfile("test.png"); // 테스트용 프로필 사진 경로

        Member result = null;

        try {
            result = memberService.updateProfile(member);
        } catch (Exception e) {
            log.error("프로필 사진 변경 실패", e);
            throw e;
        }

        if (result != null) {
            log.debug("프로필 사진 변경 성공: " + result.toString());
        }
    }


    @Test
    @DisplayName("비밀번호 변경 테스트")
    void updatePassword() throws Exception {
        Member member = new Member();
        member.setMemberId(6); // 테스트 대상 회원 ID
        member.setUserPw("123qwe!@#");
        member.setNewUserPw("1111");

        Member result = null;

        try {
            result = memberService.editPw(member);
        } catch (Exception e) {
            log.error("비밀번호 변경 실패", e);
            throw e;
        }

        if (result != null) {
            log.debug("비밀번호 변경 성공: " + result.toString());
        }
    }


}
