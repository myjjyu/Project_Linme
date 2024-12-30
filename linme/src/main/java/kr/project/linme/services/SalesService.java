package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.Sales;

public interface SalesService {
    public void addItem(Sales input) throws Exception;

    public Sales editItem(Sales params) throws Exception;

    public int deleteItem() throws Exception;

    public Sales getItem(Sales params) throws Exception;

    public List<Sales> getListW() throws Exception;

    public List<Sales> getListM() throws Exception;

    public int getCount(Sales params) throws Exception;
}
