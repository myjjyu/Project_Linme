package kr.project.linme.services.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.PaymentMapper;
import kr.project.linme.models.Payment;
import kr.project.linme.services.PaymentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public Payment addItem(Payment input) throws Exception {
        int rows = 0;

        try {
            rows = paymentMapper.insert(input);

            if (rows == 0) {
                throw new Exception("저장된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return paymentMapper.selectItem(input);
    }

    @Override
    public Payment editItem(Payment input) throws Exception {
        int rows = 0;

        try {
            rows = paymentMapper.update(input);

            if (rows == 0) {
                // 수정된 데이터가 없을 경우에도 insert를 수행하도록 수정
                paymentMapper.insert(input);
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return paymentMapper.selectItem(input);
    }

    @Override
    public int deleteItem(Payment input) throws Exception {
        int rows = 0;

        try {
            rows = paymentMapper.delete(input);

            if (rows == 0) {
                throw new Exception("삭제된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 삭제에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }

    @Override
    public Payment getItem(Payment input) throws Exception {
        Payment output = null;

        try {
            output = paymentMapper.selectItem(input);

            if (output == null) {
                throw new Exception("주문/결제에서 조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public List<Payment> getList(Payment input) throws Exception {
        List<Payment> output = null;

        try {
            output = paymentMapper.selectList(input);
        } catch (Exception e) {
            log.error("데이터 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public int getCount(Payment input) throws Exception {
        int output = 0;

        try {
            output = paymentMapper.selectCount(input);
        } catch (Exception e) {
            log.error("데이터 집계에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public int deleteByCancelOrder() throws Exception {
        int rows = 0;

        try {
            // 삭제할 payment_id 목록을 조회합니다
            List<Integer> paymentIds = paymentMapper.selectPaymentId();

            // paymentIds가 null인 경우 빈 리스트로 초기화합니다
            if (paymentIds == null) {
                paymentIds = Collections.emptyList();
            }

            if (!paymentIds.isEmpty()) {
                // payment 레코드를 삭제합니다
                rows = paymentMapper.deleteByCancelOrder(paymentIds);
            }
        } catch (Exception e) {
            log.error("삭제 실패", e);
            throw e;
        }

        return rows;
    }
    
}