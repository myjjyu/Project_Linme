package kr.project.linme.services.impl;

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
                throw new Exception("수정된 데이터가 없습니다.");
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
                throw new Exception("조회된 데이터가 없습니다.");
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
    public int overCount(Payment input) throws Exception {
        int output = 0;

        try {
            output = paymentMapper.overCount(input);

        } catch (Exception e) {
            log.error("중복여부 확인에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public int deleteByCancelOrder() throws Exception {
        int rows = 0;

        try {
            rows = paymentMapper.deleteByCancelOrder();
        } catch (Exception e) {
            log.error("삭제 실패", e);
            throw e;
        }

        return rows;
    }
    
}