package kr.project.linme;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.mappers.CategoryMapper;
import kr.project.linme.models.Category;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CategoryMapperTest {

  @Autowired
  private CategoryMapper categoryMapper;


@Test
@DisplayName("카테고리 추가 테스트")
void insertCategorys() {
  Category input = new Category();
    input.setCategoryName("영양제");
    input.setIconCategory("혈액순환");
    input.setTitleCategory("비타민충전타임");
    input.setHeaderItem("신상품");
    // input.setRegDate("now()");
    // input.setEditDate("now()");

    int output = categoryMapper.insert(input);

    log.debug("output: {}", output);
    log.debug("new Category id: {}", input.getCategoryId());
}


  @Test
  @DisplayName("카테고리 수정 테스트")
  void updateCategorys() {
    Category input = new Category();
    input.setCategoryId(1);
    input.setCategoryName("비타민");
    input.setIconCategory("눈건강");
    input.setTitleCategory("장 건강 케어");
    input.setHeaderItem("베스트");

    int output = categoryMapper.update(input);

    log.debug("output: " + output);
  }

  @Test
  @DisplayName("카테고리 삭제 테스트")
  void deleteCategorys() {
    Category input = new Category();
    input.setCategoryId(2);

    int output = categoryMapper.delete(input);

    log.debug("output: " + output);
  }

  @Test
  @DisplayName("카테고리 조회 테스트")
  void selectOneCategorys() {
    Category input = new Category();
    input.setCategoryId(3);
    input.setCategoryName("영양제");

    Category output = categoryMapper.selectItem(input);
    log.debug("output: " + output.toString());
  }

  
  @Test
  @DisplayName(" 카테고리 다중 조회 테스트")
  void selectListCategorys() {
    List<Category> output = categoryMapper.selectList(null);

    for (Category item : output) {
      log.debug("output: " + item.toString());
    }
  }


  @Test
  @DisplayName("조건에 맞는 상품 목록 조회 테스트")
  void selectWhereCategory() {
    Category input = new Category();
        input.setCategoryName("영양제"); // categoryId 값
    
        // 조건에 맞는 상품 목록 조회
        List<Category> output = categoryMapper.selectWhereList(input);
  
      // 조회된 상품 개수 출력
      log.debug("조건에 맞는 상품 목록: " + output);
  }
}