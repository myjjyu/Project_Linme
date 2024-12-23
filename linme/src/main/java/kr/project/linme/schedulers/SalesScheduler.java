package kr.project.linme.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import kr.project.linme.services.SalesService;
import lombok.extern.slf4j.Slf4j;

/**
 * 스케쥴러 샘플 클래스
 * 각 매서드가 정해진 스케쥴에 따라 자동 실행된다
 * 
 * 프로그램명 Application.java 파일의 상단에 "@EnableScheduling"이 추가되어야한다
 */
@Slf4j
@Component
@EnableAsync
public class SalesScheduler {

    @Autowired
    private SalesService salesService;
    
    // @Scheduled(cron= "0 0 4 * * ?") // 매일 오전 4시에 자동 실행
    @Scheduled(cron= "0 0 1 * * ?") // 매일 오전 1시에 자동 실행
    public void deleteByCancelOrder() throws InterruptedException {
        log.debug("판매 집계 시작");

        try {
            log.debug("판매 집계 데이터 추가");
            salesService.addItem();
        } catch (Exception e) {
            log.error("판매 집계 데이터 추가 실패");
            return;
        }
    }
}