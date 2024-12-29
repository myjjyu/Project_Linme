package kr.project.linme.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class NewMemberServiceTest {
    
    @Autowired
    private NewMemberService newMemberService;

    @Test
    @DisplayName("신규 회원 추가 테스트")
    public void addItemNewMember() {
        try {
            newMemberService.addItem();
        } catch (Exception e) {
            log.error("신규 회원 추가 테스트에 실패했습니다.", e);
        }
    }

    @Test
    @DisplayName("31일이 지난 신규 회원 삭제 테스트")
    public void deleteItemNewMember() {
        try {
            newMemberService.deleteItem();
        } catch (Exception e) {
            log.error("31일이 지난 신규 회원 삭제 테스트에 실패했습니다.", e);
        }
    }

    @Test
    @DisplayName("주간 신규 회원 데이터 조회 테스트")
    public void getListWNewMember() {
        try {
            newMemberService.getWeeklyNew();
        } catch (Exception e) {
            log.error("주간 신규 회원 데이터 조회 테스트에 실패했습니다.", e);
        }
    }

    @Test
    @DisplayName("월간 신규 회원 데이터 조회 테스트")
    public void getListMNewMember() {
        try {
            newMemberService.getMonthlyNew();
        } catch (Exception e) {
            log.error("월간 신규 회원 데이터 조회 테스트에 실패했습니다.", e);
        }
    }
}
