package kr.project.linme;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.project.linme.mappers.PaymentMapper;
import kr.project.linme.models.Payment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PaymentMapperTest {
    @Autowired
    private PaymentMapper paymentMapper;

    @Test
    @DisplayName("결제 테스트")
    void insertpayment(){
        Payment input=new Payment();
        input.setPaymentId(3);
        input.setOrderName("린미");
        input.setOrderTel("010-1234-5678");
        input.setAddr1("서울특별시 성동구 성수로 19");
        input.setAddr2("집");
        input.setAddrName("집");
        input.setAddrMsg("도착 시 연락부탁드립니다.");
        input.setDiscountPrice(3000);
        input.setTotalPrice(45000);

        int output=paymentMapper.insert(input);

        log.debug("첫번째 결제"+output);
        log.debug("new payment id: "+input.getPaymentId());
    }
}
