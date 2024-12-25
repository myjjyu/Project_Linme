package kr.project.linme.services;

import java.util.List;
import kr.project.linme.models.Profit;

public interface ProfitService {

    public List<Profit> CategorySale() throws Exception; // 카테고리별 판매량, 판매액 조회

    public void CategoryProfit() throws Exception; // CategoryProfit 테이블 생성

    public void insertCategoryProfit() throws Exception; // CategoryProfit 데이터 삽입
}
