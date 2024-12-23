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

    @GetMapping("/api/sales/list")
    public Map<String, Object> salesList() {

        List<Sales> salesList;
        try {
            // SalesService를 사용하여 판매 목록을 가져옴
            salesList = salesService.getList(new Sales());
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("salesList", salesList);

        // JSON 응답으로 반환
        return restHelper.sendJson(data);
    }
}
