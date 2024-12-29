package kr.project.linme.mappers;

import kr.project.linme.models.Profit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class ProfitMapperTest {

    @Autowired
    private ProfitMapper profitMapper;

    @Test
    @DisplayName("판매량 데이터 추가 테스트")
    void insertProfit() {
        try {
            int output = profitMapper.insert();
            log.debug("판매량 데이터 추가 성공, 추가된 행 수: {}", output);
        } catch (Exception e) {
            log.error("판매량 데이터 추가 테스트 실패", e);
        }
    }

    @Test
    @DisplayName("판매량 데이터 단일 조회 테스트")
    void selectItemProfit() {
        try {
            Profit input = new Profit();
            input.setProfitId(1); // 조회할 데이터 ID 설정

            Profit output = profitMapper.selectItem(input);
            if (output != null) {
                log.debug("판매량 데이터 단일 조회 성공: {}", output.toString());
            } else {
                log.debug("판매량 데이터 단일 조회 결과 없음");
            }
        } catch (Exception e) {
            log.error("판매량 데이터 단일 조회 테스트 실패", e);
        }
    }

    @Test
    @DisplayName("판매량 데이터 삭제 테스트")
    void deleteProfit() {
        try {
            Profit input = new Profit();
            input.setProfitId(1); // 삭제할 데이터 ID 설정

            int output = profitMapper.delete(input);
            log.debug("판매량 데이터 삭제 성공, 삭제된 행 수: {}", output);
        } catch (Exception e) {
            log.error("판매량 데이터 삭제 테스트 실패", e);
        }
    }

    @Test
    @DisplayName("판매량 주간 목록 조회 테스트")
    void selectListProfitW() {
        try {
            List<Profit> output = profitMapper.selectWeeklyProfit();
            if (!output.isEmpty()) {
                log.debug("판매량 주간 목록 조회 성공, 데이터 개수: {}", output.size());
                output.forEach(item -> log.debug("{}", item.toString()));
            } else {
                log.debug("판매량 주간 목록 조회 결과 없음");
            }
        } catch (Exception e) {
            log.error("판매량 주간 목록 조회 테스트 실패", e);
        }
    }

    @Test
    @DisplayName("판매량 월간 목록 조회 테스트")
    void selectListProfitM() {
        try {
            List<Profit> output = profitMapper.selectMonthlyProfit();
            if (!output.isEmpty()) {
                log.debug("판매량 월간 목록 조회 성공, 데이터 개수: {}", output.size());
                output.forEach(item -> log.debug("{}", item.toString()));
            } else {
                log.debug("판매량 월간 목록 조회 결과 없음");
            }
        } catch (Exception e) {
            log.error("판매량 월간 목록 조회 테스트 실패", e);
        }
    }
}
