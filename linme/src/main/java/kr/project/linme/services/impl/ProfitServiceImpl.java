package kr.project.linme.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.ProfitMapper;
import kr.project.linme.models.Profit;
import kr.project.linme.services.ProfitService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfitServiceImpl implements ProfitService {

    @Autowired
    private ProfitMapper profitMapper;


    // 카테고리별 판매량, 판매액 조회
    @Override
    public List<Profit> CategorySale() {
        try {
            return profitMapper.CategorySale();
        } catch (Exception e) {
            log.error("카테고리별 판매량, 판매액 조회에 실패했습니다.", e);
            throw new RuntimeException("카테고리별 판매량, 판매액 조회 중 오류가 발생했습니다.");
        }
    }

    // CategoryProfit 테이블 생성
    @Override
    public void CategoryProfit() {
        try {
            profitMapper.CategoryProfit();
        } catch (Exception e) {
            log.error("CategoryProfit 테이블 생성에 실패했습니다.", e);
            throw new RuntimeException("CategoryProfit 테이블 생성 중 오류가 발생했습니다.");
        }
    }

    // CategoryProfit 데이터 삽입
    @Override
    public void insertCategoryProfit() {
        try {
            profitMapper.insertCategoryProfit();
        } catch (Exception e) {
            log.error("CategoryProfit 데이터 삽입에 실패했습니다.", e);
            throw new RuntimeException("CategoryProfit 데이터 삽입 중 오류가 발생했습니다.");
        }
    }
}
