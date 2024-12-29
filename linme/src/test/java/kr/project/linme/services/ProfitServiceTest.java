package kr.project.linme.services;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.models.Profit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ProfitServiceTest {

    @Autowired
    private ProfitService profitService;

    @Test
    @DisplayName("판매량 집계 추가 테스트")
    public void addItemProfit() {
        try {
            int rows = profitService.addItem();
            log.debug("판매량 집계 추가 테스트 성공, 추가된 행 수: " + rows);
        } catch (Exception e) {
            log.error("판매량 집계 추가 테스트에 실패했습니다.", e);
        }
    }

    @Test
    @DisplayName("판매량 데이터 삭제 테스트")
    public void deleteItemProfit() {
        try {
            Profit input = new Profit();
            input.setProfitId(1); // 삭제할 데이터 ID
            int rows = profitService.deleteItem(input);
            log.debug("판매량 데이터 삭제 테스트 성공, 삭제된 행 수: " + rows);
        } catch (Exception e) {
            log.error("판매량 데이터 삭제 테스트에 실패했습니다.", e);
        }
    }

    @Test
    @DisplayName("주간 판매량 데이터 조회 테스트")
    public void getWeeklyProfit() {
        try {
            List<Profit> weeklyProfit = profitService.getWeeklyProfit();
            log.debug("주간 판매량 데이터 조회 테스트 성공");
            for (Profit profit : weeklyProfit) {
                log.debug(profit.toString());
            }
        } catch (Exception e) {
            log.error("주간 판매량 데이터 조회 테스트에 실패했습니다.", e);
        }
    }

    @Test
    @DisplayName("월간 판매량 데이터 조회 테스트")
    public void getMonthlyProfit() {
        try {
            List<Profit> monthlyProfit = profitService.getMonthlyProfit();
            log.debug("월간 판매량 데이터 조회 테스트 성공");
            for (Profit profit : monthlyProfit) {
                log.debug(profit.toString());
            }
        } catch (Exception e) {
            log.error("월간 판매량 데이터 조회 테스트에 실패했습니다.", e);
        }
    }
}
