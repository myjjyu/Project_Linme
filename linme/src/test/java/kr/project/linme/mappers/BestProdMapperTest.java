package kr.project.linme.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
  }
