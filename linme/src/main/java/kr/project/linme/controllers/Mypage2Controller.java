package kr.project.linme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
public class Mypage2Controller {

    @GetMapping("/mypage/coupon")
    @Operation(summary = "쿠폰 페이지", description = "쿠폰 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "쿠폰 페이지에 이동하였습니다."),
    })
    public String coupon(Model model) {
        return "mypage/coupon";
    }

    @GetMapping("/mypage/review")
    @Operation(summary = "리뷰 페이지", description = "리뷰 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "리뷰 페이지에 이동하였습니다."),
    })
    public String review(Model model) {
        return "mypage/review";
    }

    /* /mypage/shopping/order => PaymentController.java 이동 */
    
    // @GetMapping("/mypage/shopping/claim")
    // public String claim(Model model) {
    //     return "mypage/shopping/claim";
    // }
}
