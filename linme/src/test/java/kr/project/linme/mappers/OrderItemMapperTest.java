package kr.project.linme.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.models.OrderItem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class OrderItemMapperTest {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Test
    @DisplayName("주문 테스트")
    void insertOrderItem(){

        //주문 추가
        OrderItem input=new OrderItem();
        input.setOrderItemId(1);
        input.setOrderBname("뉴에이치헬스케어");
        input.setOrderPname("[카카홀릭]키즈 멀티바이오자임 효소 초코맛_1박스(3g*30포, 총 90g)(1개월분)");
        input.setOrderCount(1);
        input.setOrderSprice(30000);
        input.setOrderImg("/product/vitamin/vitamin1.jpg");
        input.setPaymentId(1);

        int output=orderItemMapper.insert(input);

        log.debug("주문"+output);
        log.debug("new orderItem id: "+input.getOrderItemId());
    }

    // 수정 테스트
    @Test
    @DisplayName("수정 테스트")
    void updateOrderItem(){
        OrderItem input=new OrderItem();
        
        input.setOrderItemId(1);

        input.setOrderBname("뉴에이치헬스케어");
        input.setOrderPname("[아이튼]퓨어 프로폴리스_1박스(20g*15포, 총 300g)(1개월분)");
        input.setOrderCount(1);
        input.setOrderSprice(30000);
        input.setOrderImg("/product/vitamin/vitamin1.jpg");

        int output=orderItemMapper.update(input);

        log.debug("output: "+output);
    }

    // 삭제 테스트
    @Test
    @DisplayName("삭제 테스트")
    void deleteOrderItem(){
        OrderItem input=new OrderItem();
        input.setOrderItemId(1);

        int output =orderItemMapper.delete(input);
        log.debug("output: "+output);
    }
    
    // 한 개의 주문 목록 조회 테스트
    @Test
    @DisplayName("한개의 목록 조회 테스트")
    void selectItemOrderItem(){
        OrderItem input=new OrderItem();
        input.setOrderItemId(2);

        OrderItem output=orderItemMapper.selectItem(input);
        log.debug("output: "+ output.toString());
    }

    // 주문 목록 조회 테스트
    @Test
    @DisplayName("목록 조회 테스트")
    void selectListOrderItem(){
        List<OrderItem> output=orderItemMapper.selectList(null);

        for (OrderItem item : output) {
            log.debug("output: " + item.toString());
        }
    }
}
