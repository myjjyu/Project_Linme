package kr.project.linme.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.models.BestProd;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BestProdMapperTest {

    @Autowired
    BestProdMapper bestProdMapper;

    @Test
    @DisplayName("베스트상품 집계 테스트")

    void insertBestProds() {
        int output = bestProdMapper.insert();
        log.debug("output: " + output);
    }

    @Test
    @DisplayName("베스트상품 한달 집계 테스트")
    void selectMList() {
        List<BestProd> output = bestProdMapper.selectMList();

        for (BestProd item : output) {
            log.debug("output: " + item.toString());
        }
    }

    @Test
    @DisplayName("베스트상품 일주일 집계 테스트")
    void selectWList() {

        List<BestProd> output = bestProdMapper.selectWList();

        for (BestProd item : output) {
            log.debug("output: " + item.toString());
        }
    }
}