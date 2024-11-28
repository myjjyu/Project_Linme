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
    input.setProductName("[뉴트리어스]국내최초 100% 식물성 비타민B컴플렉스_1박스(500mg*60캡슐, 총 30g)(2개월분)");
    input.setBrandId(4);
    input.setPrice(50000);
    input.setSalePrice(30000);
    input.setDiscountRate(40);
    input.setCategoryId(2);


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

    input.setCategoryId(3);


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

    int output = productMapper.selectCount(input);

    log.debug("output: {}", output);
  }
}
