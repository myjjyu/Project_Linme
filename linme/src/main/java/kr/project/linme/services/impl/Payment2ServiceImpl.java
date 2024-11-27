package kr.project.linme.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.Payment2Mapper;
import kr.project.linme.models.Payment;
import kr.project.linme.services.Payment2Service;

@Service
public class Payment2ServiceImpl implements Payment2Service {

    @Autowired
    private Payment2Mapper paymentMapper;


    @Override
    public void saveAddressName(Payment input) throws Exception {
        try {
            int result = paymentMapper.saveAddressName(input);
            if (result == 0) {
                throw new Exception("해당 정보가 존재하지 않습니다.");
            }
        } catch (Exception e) {
            throw new Exception("배송지명 수정 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
