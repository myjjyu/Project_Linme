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
    @DisplayName("카테고리 판매수량 조회 테스트")
    public void getList() {
        try {
          profitService.getList(null);
          List<Profit> profits = profitService.getList(null);
        } catch (Exception e) {
            log.error(" 수량 조회 실패", e);
        }
    }
}
