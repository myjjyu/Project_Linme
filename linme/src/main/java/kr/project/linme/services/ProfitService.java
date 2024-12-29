package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.Profit;

public interface ProfitService {
    public int addItem() throws Exception;

    public Profit editItem(Profit input) throws Exception;

    public int deleteItem(Profit input) throws Exception;

    public Profit getItem(Profit input) throws Exception;

    
    /**
     * 주간 판매량 데이터 조회
     * @param input
     * @return 주간 판매 데이터 목록
     * @throws Exception
     */
    public List<Profit> getWeeklyProfit() throws Exception;

    /**
     * 월간 판매량 데이터조회
     * @param input
     * @return 월간 판매 데이터 목록
     * @throws Exception
     */
    public List<Profit> getMonthlyProfit() throws Exception;

    public int getCount(Profit input) throws Exception;
}
