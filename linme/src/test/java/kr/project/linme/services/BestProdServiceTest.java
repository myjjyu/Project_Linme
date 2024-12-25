package kr.project.linme.services;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.models.BestProd;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BestProdServiceTest {
    @Autowired
    private BestProdService bestProdService;

    @Test
    @DisplayName("인기상품 집계 테스트")
    public void insert() {
        try {
          bestProdService.addItem();
          List<BestProd> bestProds = bestProdService.getList();
        } catch (Exception e) {
            log.error("인기상품 집계 실패", e);
        }
    }
}
