package kr.project.linme.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.models.Sales;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SalesServiceTest {
    @Autowired
    private SalesService salesService;

    @Test
    @DisplayName("매출 집계 추가 테스트")
    public void addItemSales() {
        Sales input = new Sales();
        try {
            salesService.addItem(input);
        } catch (Exception e) {
            log.error("매출 집계 추가 테스트에 실패했습니다.", e);
            return;
        }
    }

    @Test
    @DisplayName("31일이 지난 매출 집계 삭제 테스트")
    public void deleteItemSales() {
        try {
            salesService.deleteItem();
        } catch (Exception e) {
            log.error("31일이 지난 매출 집계 삭제제 테스트에 실패했습니다.", e);
        }
    }

    @Test
    @DisplayName("주간 매출 집계 데이터 조회 테스트")
    public void getListWSales() {
        try {
            salesService.getListW();
        } catch (Exception e) {
            log.error("주간 매출 집계 데이터 조회 테스트에 실패했습니다.", e);
        }
    }

    @Test
    @DisplayName("월간 매출 집계 데이터 조회 테스트")
    public void getListMSales() {
        try {
            salesService.getListM();
        } catch (Exception e) {
            log.error("월간 매출 집계 데이터 조회 테스트에 실패했습니다.", e);
        }
    }
}
