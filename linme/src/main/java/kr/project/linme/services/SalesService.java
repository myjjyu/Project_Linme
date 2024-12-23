package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.Sales;

public interface SalesService {
    public void addItem() throws Exception;

    public Sales editItem(Sales params) throws Exception;

    public int deleteItem(Sales params) throws Exception;

    public Sales getItem(Sales params) throws Exception;

    public List<Sales> getList(Sales params) throws Exception;

    public int getCount(Sales params) throws Exception;
}
