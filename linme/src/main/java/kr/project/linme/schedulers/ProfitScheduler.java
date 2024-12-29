package kr.project.linme.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

        try {
            log.debug("판매 집계 데이터 추가");
            profitService.addItem();
        } catch (Exception e) {
            log.error("판매 집계 데이터 추가 실패");
            return;
        }
    }
}
