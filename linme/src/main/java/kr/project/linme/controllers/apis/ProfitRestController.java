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
import kr.project.linme.models.Profit;
import kr.project.linme.services.ProfitService;


@RestController
@Tag(name = "Profit API", description = "판매량 집계 API")
public class ProfitRestController {

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private ProfitService profitService;

    /**
     * 전체 주문 목록 조회 API
     * @return JSON 데이터
     */
    @GetMapping("/api/profit")
    @Operation(summary = "판매량 조회", description = "월간, 주간 판매량을 조회합니다")
    public Map<String, Object> getProfitList() {
        
        List<Profit> selectWeeklyProfit;
        List<Profit> selectMonthlyProfit;

        try {
            // ProfitService를 사용하여 주간 판매량 데이터를 가져옴
            selectWeeklyProfit = profitService.getWeeklyProfit();
            // ProfitService를 사용하여 월간 판매량 데이터를 가져옴
            selectMonthlyProfit = profitService.getMonthlyProfit();
        } catch (Exception e) {
            return restHelper.serverError(e);
        }
        
        Map<String, Object> data = new LinkedHashMap<String, Object>();

        // 주간 및 월간 판매량 집계 데이터를 Map에 추가
        data.put("weekly", selectWeeklyProfit);
        data.put("monthly", selectMonthlyProfit);

        // JSON 응답으로 반환
        return restHelper.sendJson(data);
    }
}
