package kr.project.linme.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.models.Sales;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SalesMapperTest {
    @Autowired
    private SalesMapper salesMapper;
    
    @Test
    @DisplayName("매출 집계 추가 테스트")
    public void insertSales() {
        Sales output = new Sales();
        salesMapper.insert(output);
        log.debug("output: " + output);
    }

    @Test
    @DisplayName("매출 집계 월간 목록 조회 테스트")
    public void selectListSalesW() {

        List<Sales> output = salesMapper.selectListW();
        
        // 향상된 forEach문을 사용한 목록 출력
        for (Sales item : output) {
            log.debug("output: " + item.toString());
        }
    }

    @Test
    @DisplayName("매출 집계 월간 목록 조회 테스트")
    public void selectListSalesM() {

        List<Sales> output = salesMapper.selectListM();
        
        // 향상된 forEach문을 사용한 목록 출력
        for (Sales item : output) {
            log.debug("output: " + item.toString());
        }
    }
}
