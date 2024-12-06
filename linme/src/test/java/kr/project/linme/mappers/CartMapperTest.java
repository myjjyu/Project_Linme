package kr.project.linme.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.models.Cart;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CartMapperTest {
    // 실행하려는 메서드를 테스트하기 위해 의존성 주입
    @Autowired
    private CartMapper cartMapper;

    @Test
    @DisplayName("장바구니 추가 테스트")
    void insertCart() {
        Cart input = new Cart();
        // 장바구니에 추가할 데이터
        input.setProductCount(1);
        input.setMemberId(1);
        input.setProductId(1);

        int ouput = cartMapper.insert(input);

        // 저장된 데이터의 수
        log.debug("output: " + ouput);
        // 생성된 PK값
        log.debug("New CartId : " + input.getCartId());
    }

    @Test
    @DisplayName("장바구니 updateByUnique 테스트")
    void updateByUniqueCart() {
        Cart input = new Cart();
        // 장바구니에 추가할 데이터
        input.setProductCount(1);
        input.setMemberId(2);
        input.setProductId(2);

        int ouput = cartMapper.updateByUnique(input);

        // 저장된 데이터의 수
        log.debug("output: " + ouput);
    }

    @Test
    @DisplayName("장바구니 수량 증가 테스트")
    void updateCountCart() {
        Cart input = new Cart();
        // 수정할 데이터 일련번호
        input.setCartId(10);
        // 수정할 데이터
        input.setMemberId(1);
        input.setProductId(2);
        input.setProductCount(10);

        int output = cartMapper.updateCount(input);

        // 저장된 데이터의 수
        log.debug("output: " + output);
    }

    @Test
    @DisplayName("장바구니 상품 수량 변경 테스트")
    void updateCart() {
        Cart input = new Cart();
        // 수정할 데이터 일련번호
        input.setCartId(10);
        // 수정할 데이터
        input.setProductCount(7);

        int output = cartMapper.update(input);

        // 저장된 데이터의 수
        log.debug("output: " + output);
    }

    @Test
    @DisplayName("장바구니 삭제 테스트")
    void deleteCart() {
        Cart input = new Cart();
        // 삭제할 데이터 일련번호
        input.setCartId(19);
        
        int output = cartMapper.delete(input);
        log.debug("output: " + output);
    }

    @Test
    @DisplayName("장바구니 전체 삭제 테스트")
    void deleteListCart() {
        Cart input = new Cart();
        input.setMemberId(2);
        
        // 장바구니 전체 삭제
        int output = cartMapper.deleteList(input);
        log.debug("output: " + output);
    }

    @Test
    @DisplayName("장바구니 단일 조회 테스트")
    void selectItemCart() {
        Cart input = new Cart();
        // 조회할 데이터 일련번호
        input.setMemberId(1);
        input.setCartId(22);
        
        Cart output = cartMapper.selectItem(input);
        log.debug("output: " + output.toString());
    }

    @Test
    @DisplayName("장바구니 전체 목록 조회 테스트")
    void selectListCart() {
        Cart input = new Cart();
        input.setMemberId(2);
        List<Cart> output = cartMapper.selectList(input);
        
        // 향상된 forEach문을 사용한 목록 출력
        for (Cart item : output) {
            log.debug("output: " + item.toString());
        }
    }

    @Test
    @DisplayName("장바구니에 담긴 상품의 총 금액을 조회")
    void sumTotalPriceCart() {
        Cart input = new Cart();
        input.setMemberId(2);
        
        int output = cartMapper.sumTotalPrice(input);
        log.debug("output: " + output);
    }
}
