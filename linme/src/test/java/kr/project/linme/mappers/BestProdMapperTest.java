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
  @DisplayName("베스트상품 집계 및 삽입 테스트")
  // 최근 1개월간 주문된 상품 중 인기 상품 10개 집계
  void insertBestProds() {
      int output = bestProdMapper.insertBestProds();
      log.debug("output: " + output);
  }


  @Test
  @DisplayName("베스트상품 일별 집계 테스트")
  //   일별 집계
  void dailyBestProds() {
      List<BestProd> output = bestProdMapper.dailyBestProds();
      log.debug("output: " + output);
  }

  @Test
  @DisplayName("베스트상품 주별 집계 테스트")
  void weeklyBestProds() {
      List<BestProd> output = bestProdMapper.weeklyBestProds();
      log.debug("output: " + output);
  }
  }
