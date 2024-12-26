package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.Profit;

public interface ProfitService {
    public int addItem(Profit input) throws Exception; // 반환 타입 수정

    public Profit editItem(Profit input) throws Exception;

    public int deleteItem(Profit input) throws Exception;

    public Profit getItem(Profit input) throws Exception;

    public List<Profit> getList(Profit input) throws Exception;

    public int getCount(Profit input) throws Exception;
}
