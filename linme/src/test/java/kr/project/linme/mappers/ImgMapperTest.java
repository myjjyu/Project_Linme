package kr.project.linme.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.models.Img;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ImgMapperTest {
    // 실행하려는 메서드를 테스트하기 위해 의존성 주입
    @Autowired
    private ImgMapper imgMapper;

    @Test
    @DisplayName("이미지 추가 테스트")
    void insertImg() {
        Img input = new Img();
        // 이미지에 추가할 데이터
        input.setProductId(6); // 어떤 상품의 이미지인지
        input.setImgUrl("http://www.test.com"); // 이미지 URL
        input.setImgPath("C:/test"); // 이미지 경로
        input.setImg1("img1.jpg"); // 이미지1
        input.setImg2("img2.jpg"); // 이미지2
        input.setImg3("img3.jpg"); // 이미지3
        input.setDImg1("d_img1.jpg"); // 상세 이미지1
        input.setDImg2("d_img2.jpg"); // 상세 이미지2
        input.setDImg3("d_img3.jpg"); // 상세 이미지3

        int ouput = imgMapper.insert(input);

        // 저장된 데이터의 수
        log.debug("output: " + ouput);
        // 생성된 PK값
        log.debug("New ImgId : " + input.getImgId());
    }

    @Test
    @DisplayName("이미지 수정 테스트")
    void updateImg() {
        Img input = new Img();
        // 수정할 데이터 일련번호
        input.setImgId(1);
        // 수정할 데이터
        input.setProductId(1); // 어떤 상품의 이미지인지
        input.setImgUrl("http://www.test.com"); // 이미지 URL
        input.setImgPath("C:/test"); // 이미지 경로
        input.setImg1("img1.jpg"); // 이미지1
        input.setImg2("img2.jpg"); // 이미지2
        input.setImg3("img3.jpg"); // 이미지3
        input.setDImg1("d_img1.jpg"); // 상세 이미지1
        input.setDImg2("d_img2.jpg"); // 상세 이미지2
        input.setDImg3("d_img3.jpg"); // 상세 이미지3

        int ouput = imgMapper.update(input);

        // 저장된 데이터의 수
        log.debug("output: " + ouput);
    }

    @Test
    @DisplayName("이미지 삭제 테스트")
    void deleteImg() {
        Img input = new Img();
        // 삭제할 데이터 일련번호
        input.setImgId(1);
        
        int output = imgMapper.delete(input);
        log.debug("output: " + output);
    }

    @Test
    @DisplayName("하나의 이미지 목록 조회 테스트")
    void selectItemImg() {
        Img input = new Img();
        // 조회할 데이터 일련번호
        input.setImgId(2);
        
        Img output = imgMapper.selectItem(input);
        log.debug("output: " + output.toString());
    }

    @Test
    @DisplayName("이미지 목록 조회 테스트")
    void selectListImg() {
        List<Img> output = imgMapper.selectList(null);
        
        // 향상된 forEach문을 사용한 목록 출력
        for (Img item : output) {
            log.debug("output: " + item.toString());
        }
    }
}
