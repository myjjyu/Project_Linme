package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.Payment;

public interface PaymentService {
    public Payment addItem(Payment params) throws Exception;

    public Payment editItem(Payment params) throws Exception;

    public int deleteItem(Payment params) throws Exception;

    public Payment getItem(Payment params) throws Exception;

    public List<Payment> getList(Payment params) throws Exception;

    public int getCount(Payment params) throws Exception;

    public int deleteByCancelOrder() throws Exception;
}
