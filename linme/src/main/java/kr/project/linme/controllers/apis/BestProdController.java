package kr.project.linme.controllers.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.project.linme.services.BestProdService;

@RestController
@RequestMapping("/api/best-prods")
public class BestProdController {

    @Autowired
    private BestProdService bestProdService;

}
