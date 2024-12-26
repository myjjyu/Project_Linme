package kr.project.linme.controllers.apis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import kr.project.linme.helpers.RestHelper;
import kr.project.linme.models.Sales;
import kr.project.linme.services.SalesService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class SalesRestController {
    
    @Autowired
    private RestHelper restHelper;

    @Autowired
    private SalesService salesService;

    /**
     * 월간 매출 집계 목록 조회 API
     * @return
     */
    @GetMapping("/api/sales")
    public Map<String, Object> salesList() {

        List<Sales> salesListW;
        List<Sales> salesListM;

        try {
            // SalesService를 사용하여 주간/월간 판매 집계 데이터를 가져옴
            salesListW = salesService.getListW(new Sales());
            salesListM = salesService.getListM(new Sales());
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        // 데이터를 Map에 담아서 반환
        data.put("weekly", salesListW);
        data.put("monthly", salesListM);

        // JSON 응답으로 반환
        return restHelper.sendJson(data);
    }
}
