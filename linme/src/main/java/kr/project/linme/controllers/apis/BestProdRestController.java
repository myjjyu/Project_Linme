package kr.project.linme.controllers.apis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.project.linme.helpers.RestHelper;
import kr.project.linme.models.BestProd;
import kr.project.linme.services.BestProdService;

@RestController
public class BestProdRestController {

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private BestProdService bestProdService;
    
    @GetMapping("/api/best_prod")
    public Map<String, Object> getBestProdList() throws Exception {

        BestProd input = new BestProd();
        List<BestProd> output = null;

        try {
            output = bestProdService.getList();
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("bestProdList", output);
        return restHelper.sendJson(data);
    }
}