package kr.project.linme.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.project.linme.models.BestProd;
import kr.project.linme.services.BestProdService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@EnableAsync
public class BestProdScheduler {
    
    @Autowired
    private BestProdService bestProdService;

    @Scheduled(cron = "0 0 1 * * ?")// 매일 오전 1시에 자동 실행
    public void scheduleInsert() {
        log.debug("베스트 상품 집계 시작");
        try {
            log.debug("베스트 상품 집계");
            BestProd bestProd = new BestProd(); 
            bestProdService.addItem(bestProd);
        } catch (Exception e) {
            log.error("베스트 상품 집계 실패", e);
            return;
        }
    }
}