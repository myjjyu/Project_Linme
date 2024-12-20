package kr.project.linme.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import kr.project.linme.services.PaymentService;
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
public class PaymentScheduler {

    @Autowired
    private PaymentService paymentService;
    
    @Scheduled(cron= "0 0 4 * * ?") // 매일 오전 4시에 자동 실행
    // @Scheduled(cron= "0 */30 * * * ?") // 30분마다 자동 실행
    public void deleteByCancelOrder() throws InterruptedException {
        log.debug("주문/결제 중 결제하지 않은 주문 내역 삭제");
        
        int deletePayment = 0;

        try {
            log.debug("주문/결제 내역 삭제");
            deletePayment = paymentService.deleteByCancelOrder();
        } catch (Exception e) {
            log.error("주문/결제 내역 삭제 실패");
            return;
        }

        if (deletePayment == 0) {
            log.debug("주문 중 결제 취소 없음 :)");
            return;
        }

        log.debug("삭제된 주문 내역 수: " + deletePayment);

    }

}