package kr.project.linme.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.project.linme.models.Profit;
import kr.project.linme.services.ProfitService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProfitScheduler {

    @Autowired
    private ProfitService profitService;

    @Scheduled(cron = "0 0 1 * * ?") // 매일 오전 1시에 자동 실행
    public void addProfitData() {
        log.debug("매출 집계");

        Profit input = new Profit();
        // 필요한 필드를 설정 (예: 기본값 또는 null 값)
        input.setCategoryId(0); // 예: 모든 카테고리 기본 집계

        try {
            int result = profitService.addItem(input); // Profit 객체 전달
            log.debug("매출 집계 데이터 추가 결과: {}", result);
        } catch (Exception e) {
            log.error("매출 집계 데이터 추가 실패", e);
        }
    }
}
