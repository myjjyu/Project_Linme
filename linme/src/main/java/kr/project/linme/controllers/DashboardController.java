package kr.project.linme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {
    /**
     * 대시보드 페이지
     * @return
     */
    @GetMapping("/dashboard")
    public String Dashboard() {
        return "dashboard";
    }
}
