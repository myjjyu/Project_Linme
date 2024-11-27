package kr.project.linme.services;

import kr.project.linme.models.Payment;

public interface Payment2Service {
    
    // 주소 변경(배송지명)
    public void saveAddressName(Payment input) throws Exception;
}