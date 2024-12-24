package kr.project.linme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.BestProdMapper;
import kr.project.linme.models.BestProd;

@Service
public class BestProdService {

    @Autowired
    private BestProdMapper bestProdMapper;

    // 인기 상품 데이터 삽입
    public int insertBestProds() {
        return bestProdMapper.insertBestProds();
    }

    // 일별 인기 상품 가져오기
    public List<BestProd> getDailyBestProds() {
        return bestProdMapper.dailyBestProds();
    }

    // 주별 인기 상품 가져오기
    public List<BestProd> getWeeklyBestProds() {
        return bestProdMapper.weeklyBestProds();
    }
}
