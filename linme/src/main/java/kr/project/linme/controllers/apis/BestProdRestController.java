package kr.project.linme.controllers.apis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.project.linme.helpers.RestHelper;
import kr.project.linme.models.BestProd;
import kr.project.linme.services.BestProdService;

@RestController
@Tag(name = "BestProduct API", description = "인기상품 집계 API")

public class BestProdRestController {

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private BestProdService bestProdService;

    @GetMapping("/api/best_prod")
    @Operation(summary = "인기 상품 조회", description = "월간, 주간 인기 상품 TOP10, TOP5 을(를) 조회합니다")

    public Map<String, Object> getBestProdList() throws Exception {

        List<BestProd> output1 = null;
        List<BestProd> output2 = null;

        try {
            // 월간 인기상품
            output1 = bestProdService.selectMList();
            // 주간 인기상품
            output2 = bestProdService.selectWList();
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("monthly", output1);
        data.put("weekly", output2);
        return restHelper.sendJson(data);
    }
}




