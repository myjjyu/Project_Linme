package kr.project.linme.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kr.project.linme.models.Brand;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BrandMapperTest {
    @Autowired
    private BrandMapper brandMapper;

    @Test
    @DisplayName("브랜드 추가 테스트")
    void insertBrand() {
        Brand input = new Brand();
        // input.setBrandName("글로우밍글");
        // input.setBrandName("주식회사 에프디랩");
        input.setBrandName("파파초이스");

        int output = brandMapper.insert(input);

        log.debug("output: " + output);
        log.debug("new id: " + input.getBrandId());
    }

    @Test
    @DisplayName("브랜드 수정 테스트")
    void updateBrand() {
        Brand input = new Brand();
        input.setBrandId(4);
        input.setBrandName("오로니아, 뉴트리코리아");

        int output = brandMapper.update(input);

        log.debug("output: " + output);
    }

    @Test
    // 삭제하려는 데이터가 있는지 확인
    @DisplayName("회원 삭제 테스트")
    void deleteBrand() {
        Brand input = new Brand();
        input.setBrandId(5);

        int output = brandMapper.delete(input);
        log.debug("output" + output);
    }

    @Test
    // 조회하려는 데이터가 있는지 확인
    @DisplayName("하나의 회원 조회 테스트")
    void selectOneBrand() {
        Brand input = new Brand();
        input.setBrandId(2);

        Brand output = brandMapper.selectItem(input);
        log.debug("output: " + output.toString());
    }

    @Test
    @DisplayName("회원 목록 조회 테스트")
    void selectListBrand() {
        List<Brand> output = brandMapper.selectList(null);

        for (Brand item : output) {
            log.debug("output: " + item.toString());
        }
    }    
}
