package kr.project.linme.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import kr.project.linme.models.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@Tag(name = "MyPage API", description = "마이페이지 API")
public class MypageController {

    @GetMapping("/myPage")
    @Operation(summary = "마이페이지", description = "마이페이지 기본 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "마이페이지에 이동하였습니다.")
    })
    public String myPage(HttpSession session, Model model) {
        Member memberInfo = (Member) session.getAttribute("memberinfo");
        if (memberInfo != null) {
            // 로그인하지 않은 경우 처리
            return "redirect:/login";
        }
        model.addAttribute("memberinfo", memberInfo);
        return "myPage/myPage";
    }
    
    @GetMapping("/myPage/profile")
    @Operation(summary = "프로필 변경", description = "프로필 변경을 위한 요청을 처리.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "프로필 변경에 성공하였습니다."),
        @ApiResponse(responseCode = "400", description = "프로필 변경에 실패하였습니다.")
    })
    public String getProfilePage(HttpSession session, Model model) {
        Member memberInfo = (Member) session.getAttribute("memberInfo");

        if (memberInfo == null) {
            memberInfo = new Member();
            memberInfo.setProfile("/assets/img/myPage/profileimg.jpg"); // 기본 프로필 이미지 경로 설정
            session.setAttribute("memberInfo", memberInfo);
        } else if (memberInfo.getProfile() == null || memberInfo.getProfile().isEmpty()) {
            memberInfo.setProfile("/assets/img/myPage/profileimg.jpg"); // 기본값 설정
        }

        model.addAttribute("memberInfo", memberInfo);
        return "myPage/profile";
    }


    @GetMapping("/myPage/level")
    @Operation(summary = "등급페이지", description = "등급 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "등급 페이지에 이동하였습니다."),
    })
    public String level() {
        return "myPage/level";
    }

    @GetMapping("/myPage/reward")
    @Operation(summary = "리워드페이지", description = "리워드 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "리워드 페이지에 이동하였습니다."),
    })
    public String reward() {
        return "myPage/reward";
    }

    @GetMapping("/myPage/reward/history")
    @Operation(summary = "리워드 내역 페이지", description = "리워드 내역 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "리워드 내역 페이지에 이동하였습니다."),
    })
    public String history() {
        return "myPage/reward/history";
    }
    @GetMapping("/myPage/myinfo")
    @Operation(summary = "개인정보 수정 페이지", description = "개인정보 수정 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "개인정보 수정 페이지에 이동하였습니다."),
    })
    public String myinfo(HttpSession session, Model model) {

        Member memberInfo = (Member) session.getAttribute("memberinfo");
        if (memberInfo != null) {
            // 로그인하지 않은 경우 처리
            return "redirect:/login";
        }
        model.addAttribute("memberinfo", memberInfo);
        return "myPage/myinfo";
    }

    @GetMapping("/myPage/myinfo/modify")
    @Operation(summary = "이름, 이메일 페이지", description = "이름, 이메일 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "이름, 이메일 페이지에 이동하였습니다."),
    })
    public String modify() {
        return "/myPage/myinfo/modify";
    }

    @GetMapping("/myPage/myinfo/passwordModify")
    @Operation(summary = "비밀번호 변경 페이지", description = "비밀번호 변경 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "수정되었습니다."),
        @ApiResponse(responseCode = "400", description = "비밀번호 변경에 실패하였습니다."),
    })
    public String passwordModify() {
        return "/myPage/myinfo/passwordModify";
    }

    @GetMapping("/myPage/myinfo/notification")
    @Operation(summary = "알림 설정 페이지", description = "알림 설정 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "알림 설정 페이지에 이동하였습니다."),
    })
    public String notification() {
        return "/myPage/myinfo/notification";
    }

    @GetMapping("/myPage/myinfo/withdrawal")
    @Operation(summary = "탈퇴 페이지", description = "탈퇴 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "탈퇴되었습니다."),
        @ApiResponse(responseCode = "400", description = "탈퇴에 실패하였습니다."),
    })
    public String withdrawal() {
        return "/myPage/myinfo/withdrawal";
    }


    @GetMapping("/myPage/productInquiry")
    @Operation(summary = "상품문의 내역 페이지", description = "상품문의 내역 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "상품문의 내역 페이지에 이동하였습니다."),
    })
    public String productInquiry() {
        return "myPage/productInquiry";
    }

    @GetMapping("/myPage/refundAccount")
    @Operation(summary = "계좌번호 등록/변경 페이지", description = "계좌번호 등록/변경 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "계좌번호 등록/변경 페이지에 이동하였습니다."),
    })
    public String refundAccount() {
        return "myPage/refundAccount";
    }

    @GetMapping("/myPage/addressBook")
    @Operation(summary = "배송지 목록 페이지", description = "배송지 목록 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "배송지 목록 페이지에 이동하였습니다."),
    })
    public String addressBook() {
        return "myPage/addressBook";
    }

    @GetMapping("/myPage/addressBookAdd")
    @Operation(summary = "배송지 수정 페이지", description = "배송지 수정 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "수정되었습니다."),
        @ApiResponse(responseCode = "400", description = "배송지 변경에 실패하였습니다."),
    })
    public String addressBookAdd() {
        return "myPage/addressBookAdd";
    }

    @GetMapping("/myPage/notice")
    @Operation(summary = "공지사항 페이지", description = "공지사항 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "공지사항 페이지에 이동하였습니다."),
    })
    public String notice() {
        return "myPage/notice";
    }

    @GetMapping("/myPage/faq")
    @Operation(summary = "FAQ 페이지", description = "FAQ 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "FAQ 페이지에 이동하였습니다."),
    })
    public String faq() {
        return "myPage/faq";
    }

    @GetMapping("/myPage/inquiry")
    @Operation(summary = "1:1 문의 페이지", description = "1:1 문의 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "1:1 문의 페이지에 이동하였습니다."),
    })
    public String injury() {
        return "myPage/inquiry";
    }

    @GetMapping("/mypage/coupon")
    @Operation(summary = "쿠폰 페이지 API", description = "쿠폰 페이지 표시 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "쿠폰 페이지에 이동에 성공하였습니다."),
        @ApiResponse(responseCode = "400", description = "쿠폰 페이지에 이동에 실패하였습니다.")
    })
    public String coupon(Model model) {
        return "mypage/coupon";
    }

    @GetMapping("/mypage/review")
    @Operation(summary = "리뷰 페이지", description = "리뷰 페이지 표시.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "리뷰 페이지에 이동에 성공하였습니다."),
        @ApiResponse(responseCode = "400", description = "리뷰 페이지에 이동에 실패하였습니다.")
    })
    public String review(Model model) {
        return "mypage/review";
    }

    /* /mypage/shopping/order => PaymentController.java 이동 */
    
    @GetMapping("/mypage/shopping/claim")
    @Operation(summary = "클레임 페이지", description = "클레임 페이지.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "클레임 페이지에 이동에 성공하였습니다."),
        @ApiResponse(responseCode = "400", description = "클레임 페이지에 이동에 실패하였습니다.")
    })
    public String claim(Model model) {
        return "mypage/shopping/claim";
    }
}
