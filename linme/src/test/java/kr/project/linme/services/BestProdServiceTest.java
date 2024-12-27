package kr.project.linme.services;

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
  @DisplayName("베스트상품 집계 테스트")
  void insertBestProds() {
      try {
            BestProd input = new BestProd();
            bestProdService.insert(input);
        } catch (Exception e) {
            log.error("상품 집계 실패", e);
        }
  }

  @Test
  @DisplayName("베스트상품 한달 집계 테스트")
  public void selectMList() {
    try {
        bestProdService.selectMList();
    } catch (Exception e) {
        log.error("상품 집계 실패", e);    }
  }

  @Test
  @DisplayName("베스트상품 일주일 집계 테스트")
  public void selectWList() {
    try {
        bestProdService.selectWList();
    } catch (Exception e) {
        log.error("상품 집계 실패", e);    }
  }
}