package kr.project.linme.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class NewMemberMapperTest {

    @Autowired
    private NewMemberMapper newMemberMapper;

    @Test
    @DisplayName("주간 NEW 회원 집계 데이터 조회")
    void selectWeeklyNew() {
        newMemberMapper.selectWeeklyNew().forEach(item -> {
            log.debug("item: " + item);
        });
    }

    @Test
    @DisplayName("월간 NEW 회원 집계 데이터 조회")
    void selectMonthlyNew() {
        newMemberMapper.selectMonthlyNew().forEach(item -> {
            log.debug("item: " + item);
        });
    }
}