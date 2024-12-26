package kr.project.linme.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.models.OrderItem;
import kr.project.linme.models.Profit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ProfitMapperTest {


    @Autowired
    private ProfitMapper profitMapper;

    @Test
    @DisplayName("Profit 데이터 조회 테스트")
    void selectListProfit() {
        List<Profit> results = profitMapper.selectList(null);

        log.debug("조회된 데이터 수: {}", results.size());
        for (Profit item : results) {
            log.debug("조회 데이터: {}", item);
        }
    }

    // @Test
    // @DisplayName("Profit 데이터 삭제 테스트")
    // void deleteProfit() {
    //     Profit input = new Profit();
    //     input.setProfitId(1); // 삭제할 카테고리 ID 설정

    //     int result = profitMapper.delete(input);
    //     log.debug("DELETE 결과: {}", result);
    // }
}
