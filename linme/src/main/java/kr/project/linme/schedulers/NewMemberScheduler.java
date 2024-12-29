package kr.project.linme.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.project.linme.services.NewMemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableAsync
public class NewMemberScheduler {

    @Autowired
    private NewMemberService newMemberService;
    
    @Scheduled(cron = "0 0 1 * * ?") // 매일 오전 1시에 자동 실행
    public void scheduleInsert() {
        log.debug("====신규회원 집계 시작====");

        try {
            log.debug("신규회원 집계");
        } catch (Exception e) {
            log.error("신규회원 집계 실패", e);
            return;
        }

        try {
            log.debug("31일이 지난 신규회원 집계 데이터 삭제");
            newMemberService.deleteItem();
        } catch (Exception e) {
            log.error("31일이 지난 신규회원 집계 데이터 삭제 실패", e);
            return;
        }

        log.debug("====신규회원 집계 끝====");
    }
}
