package kr.project.linme.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.models.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ProductMapperTest {

  @Autowired
  private ProductMapper productMapper;

  // 상품 추가 테스트
  @Test
  @DisplayName("상품 추가 테스트")
  void insertProduct() {
    Product input = new Product();
    input.setProductName("[광동 메모리365]알티지rTG오메가3루테인밀크씨슬 혈행개선_1박스(740mg*60캡슐, 총 88.8g X 2개입)(2개월)");
    input.setBrandId(1);
    input.setPrice(88000);
    input.setSalePrice(50000);
    input.setDiscountRate(43);
    input.setProductImg(""); // 이미지 경로는 비워두었음
    input.setDetailImg("");
    input.setCategoryId(3);
    input.setHeaderItem("신상품");

    int output = productMapper.insert(input);

    log.debug("output: {}", output);
    log.debug("new product id: {}", input.getProductId());
  }

  // 상품 수정 테스트
  @Test
  @DisplayName("상품 수정 테스트")
  void updateProduct() {
    Product input = new Product();
    input.setProductId(6);
    input.setProductName("[네츄럴메이드]플랙씨드 앤 햄프씨드 선물세트_1박스(1,000mg*180캡슐, 총 180g X 2개입)(6개월분)");
    input.setBrandId(1);
    input.setPrice(174000);
    input.setSalePrice(52500);
    input.setDiscountRate(70);
    input.setProductImg(""); // 이미지 경로는 비워두었음
    input.setDetailImg("");
    input.setCategoryId(3);
    input.setHeaderItem("베스트");

    int output = productMapper.update(input);

    log.debug("output: {}", output);
  }

  // 상품 삭제 테스트
  @Test
  @DisplayName("상품 삭제 테스트")
  void deleteProduct() {
    Product input = new Product();
    input.setProductId(9);

    int output = productMapper.delete(input);

    log.debug("output: {}", output);
  }

  // 단일 상품 조회 테스트
  @Test
  @DisplayName("단일 상품 조회 테스트")
  void selectOneProduct() {
    Product input = new Product();
    input.setProductId(6);

    Product output = productMapper.selectItem(input);

    log.debug("output: {}", output);
  }

  // 전체 상품 조회 테스트
  @Test
  @DisplayName("전체 상품 조회 테스트")
  void selectAllProducts() {
    List<Product> output = productMapper.selectList(null);

    output.forEach(item -> log.debug("output: {}", item));
  }

  // 조건에 맞는 상품 개수 조회 테스트
  @Test
  @DisplayName("조건에 맞는 상품 개수 조회 테스트")
  void selectProductCountByCondition() {
    Product input = new Product();
    input.setCategoryId(3); // categoryId 값
    input.setHeaderItem("신상품"); // headerItem 값

    int output = productMapper.selectCount(input);

    log.debug("output: {}", output);
  }
}
