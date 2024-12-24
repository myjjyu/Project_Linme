package kr.project.linme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.project.linme.models.BestProd;
import kr.project.linme.services.BestProdService;

@RestController
@RequestMapping("/api/best-prods")
public class BestProdController {

    @Autowired
    private BestProdService bestProdService;

    // 인기 상품 데이터 삽입
    @PostMapping("/insert")
    public String insertBestProds() {
        int result = bestProdService.insertBestProds();
        return result > 0 ? "베스트 상품 집계 완료" : "집계 실패";
    }

    @GetMapping("/daily")
    public ResponseEntity<List<BestProd>> getDailyBestProds() {
        List<BestProd> bestProds = bestProdService.getDailyBestProds();
        return ResponseEntity.ok(bestProds); // JSON 형태로 반환
    }

    // 주별 인기 상품 데이터 반환
    @GetMapping("/weekly")
    public List<BestProd> getWeeklyBestProds() {
        return bestProdService.getWeeklyBestProds();
    }
}
