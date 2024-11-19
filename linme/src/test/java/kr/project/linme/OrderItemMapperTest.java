package kr.project.linme;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.mappers.OrderItemMapper;
import kr.project.linme.models.OrderItem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class OrderItemMapperTest {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Test
    @DisplayName("주문 테스트")
    void insertorder(){

        //주문 추가
        OrderItem input=new OrderItem();
        input.setOrderBname("뉴에이치헬스케어");
        input.setOrderPname("[카카홀릭]키즈 멀티바이오자임 효소 초코맛_1박스(3g*30포, 총 90g)(1개월분)");
        input.setOrderCount(1);
        input.setOrderSprice(30000);
        input.setOrderPrice(24900);
        input.setPaymentId(1);

        int output=orderItemMapper.insert(input);

        log.debug("주문"+output);
        log.debug("new orderItem id: "+input.getOrderItemId());
    }
}
