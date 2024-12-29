package kr.project.linme.controllers;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.project.linme.helpers.UtilHelper;
import kr.project.linme.helpers.WebHelper;
import kr.project.linme.services.MemberService;
import lombok.extern.slf4j.Slf4j;
import kr.project.linme.models.Member;



@Slf4j
@Controller
@Tag(name = "User API", description = "회원 관련 및 비밀번호 재생성 API")
public class UserController {

    @Autowired
    private UtilHelper utilHelper;

    @Autowired
    private WebHelper webHelper;

    @Autowired
    private MemberService memberService;

    @GetMapping("/member/findId")
    @Operation(summary = "아이디 찾기", description = "아이디 찾기를 위한 페이지를 표시한다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "아이디 찾기 페이지 표시에 성공하였습니다.")
    })
    public String findId() {
        return "member/findId";
    }

    @GetMapping("/member/findIdIn")
    @Operation(summary = "아이디 찾기 처리", description = "아이디 찾기 처리를 위한 요청을 처리한다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "아이디 찾기에 성공하였습니다."),
        @ApiResponse(responseCode = "400", description = "아이디 찾기에 실패하였습니다.")
    })
    public String findIdIn() {
        return "member/findIdIn";
    }
    
    @GetMapping("/member/findPw")
    @Operation(summary = "비밀번호 찾기", description = "비밀번호 찾기를 위한 페이지를 표시한다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "비밀번호 찾기 페이지 표시에 성공하였습니다.")
    })
    public String findPw() {
        return "member/findPw";
    }

    @GetMapping("/login")
    @Operation(summary = "로그인", description = "로그인을 위한 페이지를 표시한다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그인 페이지 표시에 성공하였습니다.")
    })
    @Parameters({
        @Parameter(name = "rememberId", description = "아이디 저장 여부", schema = @Schema(type = "string"), required = false)
    })
    public String login(Model model,
        @CookieValue(value = "rememberId", required = false) String rememberId) {
        model.addAttribute("rememberId", rememberId);
        return "login";
    }

    @GetMapping("/signUp")
    @Operation(summary = "회원가입", description = "회원가입을 위한 페이지를 표시한다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "회원가입 페이지 표시에 성공하였습니다."),
        @ApiResponse(responseCode = "400", description = "회원가입 페이지 표시에 실패하였습니다.")
    })
    public String signUp() {
        return "signUp";
    }

    @GetMapping("/member/resetPw")
    @Operation(summary = "비밀번호 재설정", description = "비밀번호 재설정을 위한 페이지를 표시한다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "비밀번호 재설정 페이지 표시에 성공하였습니다."),
        @ApiResponse(responseCode = "400", description = "비밀번호 재설정 페이지 표시에 실패하였습니다.")
    })
    public String resetPw() {
        return "member/resetPw";
    }

@PostMapping("/member/resetPwIn")
@Operation(summary = "비밀번호 재설정 처리", description = "비밀번호 재설정 처리를 위한 요청을 처리한다.")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "비밀번호 재설정에 성공하였습니다."),
    @ApiResponse(responseCode = "400", description = "비밀번호 재설정에 실패하였습니다.")
})
@Parameters({
    @Parameter(name = "user_name", description = "사용자 이름", schema = @Schema(type = "string"), required = true),
    @Parameter(name = "user_id", description = "사용자 아이디", schema = @Schema(type = "string"), required = true),
    @Parameter(name = "tel", description = "전화번호", schema = @Schema(type = "string"), required = true)
})
    public String resetPwIn(
        Model model,
        @RequestParam("user_name") String userName,
        @RequestParam("user_id") String userId,
        @RequestParam("tel") String tel
        
    ) {
        // 새로운 비밀번호 생성
        String newPw = utilHelper.randomPassword(10);

        // 입력된 사용자 정보를 기반으로 Member 객체 생성
        Member input = new Member();
        input.setUserName(userName);
        input.setUserId(userId);
        input.setTel(tel);
        input.setUserPw(newPw);

        try {
            // 비밀번호 재설정 서비스 호출
            memberService.resetPw(input);
        } catch (Exception e) {
            // 서버 오류 처리
            webHelper.serverError(e);
            return null;
        }
        // 모델에 사용자 정보와 새 비밀번호 추가
        model.addAttribute("item", input);
        model.addAttribute("password", newPw);

        // 비밀번호 재설정 완료 페이지로 이동
        return "member/resetPwIn";
    }
}
