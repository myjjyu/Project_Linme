package kr.project.linme.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kr.project.linme.models.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Member2MapperTest {
    @Autowired
    private Member2Mapper memberMapper;

    
    

    @Test
    @DisplayName("닉네임 수정 테스트")
    void updateNickname() {
        Member input = new Member();
        input.setMemberId(1); 
        input.setNickname("예예11"); // 수정할 닉네임 설정

        int output = memberMapper.updateNickname(input);

        log.debug("output: " + output);
    }

    @Test
    @DisplayName("프로필 수정 테스트")
    void updateProfile() {
        Member input = new Member();
        input.setMemberId(4); 
        input.setProfile("test.jpg"); // 테스트용 프로필 경로 설정

        int output = memberMapper.updateProfile(input);

        log.debug("output: " + output);
        assertTrue(output > 0);
    }
    @Test
    @DisplayName("비밀번호 수정 테스트")
    void updatePassword(){
        Member input = new Member();
        input.setMemberId(5);
        input.setUserPw("1234");
        input.setNewUserPw("1111");

        int output = memberMapper.updatePw(input);

        log.debug("output: " + output);
    }

    @Test
    @DisplayName("현재 비밀번호 확인 테스트")
    void CountByPassword() {
        // given: 테스트에 사용할 회원 데이터
        Member input = new Member();
        input.setMemberId(6); // 테스트용 회원 ID
        input.setUserPw("1111"); // MD5로 암호화된 값이 있는 실제 비밀번호 입력

        // when: countByPassword 메서드를 호출
        int count = memberMapper.countByPassword(input);

        // then: 결과 검증
        // count가 1이어야 비밀번호가 일치
        assertEquals(1, count, "현재 비밀번호 확인 실패");
    }

    @Test
    // 삭제하려는 데이터가 있는지 확인
    @DisplayName("회원 삭제 테스트")
    void deleteMember() {
        Member input = new Member();
        input.setMemberId(3);

        int output = memberMapper.delete(input);
        log.debug("output" + output);
    }

    @Test
    // 조회하려는 데이터가 있는지 확인
    @DisplayName("하나의 회원 조회 테스트")
    void selectOneMember() {
        Member input = new Member();
        input.setMemberId(2);

        Member output = memberMapper.selectItem(input);
        log.debug("output: " + output.toString());
        
    }

    @Test
    @DisplayName("회원 목록 조회 테스트")
    void selectListMember() {
        List<Member> output = memberMapper.selectList(null);

        for (Member item : output) {
            log.debug("output: " + item.toString());
        }
    }    
}
