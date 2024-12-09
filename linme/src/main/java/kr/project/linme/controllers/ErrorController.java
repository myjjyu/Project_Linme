package kr.project.linme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/custom-error")
public class ErrorController {
    private static final String ERROR_PATH = "/custom-error";
    
    @RequestMapping(value = ERROR_PATH)
    public String handleError(HttpServletRequest request, HttpServletResponse response) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        switch (statusCode) {
            case 404:
                return "error/404.html";
            case 500:
                return "error/500.html";
            default:
                return "error/500.html";
        }
    }
}




