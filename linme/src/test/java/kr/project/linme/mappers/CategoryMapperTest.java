package kr.project.linme.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.models.Category;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CategoryMapperTest {

  @Autowired
  private CategoryMapper categoryMapper;

  // 카테고리 추가 테스트
  @Test
  @DisplayName("카테고리 추가 테스트")
  void insertCategory() {
    Category input = new Category();
    input.setCategoryName("영양제");
    input.setHeaderItem("신상품");

    int output = categoryMapper.insert(input);

    log.debug("output: {}", output);
    log.debug("new Category id: {}", input.getCategoryId());
  }

  // 카테고리 수정 테스트
  @Test
  @DisplayName("카테고리 수정 테스트")
  void updateCategory() {
    Category input = new Category();
    input.setCategoryId(1);
    input.setCategoryName("비타민");
    input.setHeaderItem("베스트");

    int output = categoryMapper.update(input);

    log.debug("output: {}", output);
  }

  // 카테고리 삭제 테스트
  @Test
  @DisplayName("카테고리 삭제 테스트")
  void deleteCategory() {
    Category input = new Category();
    input.setCategoryId(2);

    int output = categoryMapper.delete(input);

    log.debug("output: {}", output);
  }

  // 단일 카테고리 조회 테스트
  @Test
  @DisplayName("카테고리 조회 테스트")
  void selectOneCategory() {
    Category input = new Category();
    input.setCategoryId(3);
    input.setCategoryName("영양제");

    Category output = categoryMapper.selectItem(input);

    log.debug("output: {}", output);
  }

  // 카테고리 다중 조회 테스트
  @Test
  @DisplayName("카테고리 다중 조회 테스트")
  void selectAllCategories() {
    List<Category> output = categoryMapper.selectList(null);

    output.forEach(item -> log.debug("output: {}", item));
  }

  // 조건에 맞는 카테고리 개수 조회 테스트
  @Test
  @DisplayName("조건에 맞는 카테고리 개수 조회 테스트")
  void selectCategoryCountByCondition() {
    Category input = new Category();
    input.setCategoryName("비타민");

    int output = categoryMapper.selectCount(input);

    log.debug("output: {}", output);
  }
}
