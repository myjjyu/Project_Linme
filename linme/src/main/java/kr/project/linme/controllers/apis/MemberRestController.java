package kr.project.linme.controllers.apis;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.project.linme.helpers.FileHelper;
import kr.project.linme.helpers.RestHelper;
import kr.project.linme.helpers.WebHelper;
import kr.project.linme.models.Member;
import kr.project.linme.services.MemberService;


@RestController
public class MemberRestController {

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private MemberService memberService;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private WebHelper webHelper;

    // 아이디 (이메일) 중복검사
    @GetMapping("/api/member/id_unique_checkk")
    public Map<String, Object> idUniqueCheck(@RequestParam("user_id") String userId) {
        try {
            memberService.isUniqueUserId(userId);
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }
        
        return restHelper.sendJson();
    }

    // 닉네임 중복검사
    @GetMapping("/api/member/nickname_unique_check")
    public Map<String, Object> nicknameUniqueCheck(@RequestParam("nickname") String nickname) {
        try {
            memberService.isUniqueNickname(nickname);
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }
        
        return restHelper.sendJson();
    }

    // 회원가입
    @PostMapping("/api/member/join")
    public Map<String, Object> join(
        @RequestParam("user_id") String userId,
        @RequestParam("nickname") String nickname,
        @RequestParam("pw") String userPw,
        @RequestParam("userName") String userName,
        @RequestParam("tel") String tel,
        @RequestParam("postcode") String postcode,
        @RequestParam("addr1") String addr1,
        @RequestParam("addr2") String addr2
    ) {

        // 1) 유효성 검사 생략

        // 2) 아이디(이메일) 중복 검사
        try {
            memberService.isUniqueUserId(userId);
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }

        // 3) 닉네임 중복 검사
        try {
            memberService.isUniqueNickname(nickname);
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }

        // 정보를 서비스에 전달하기 위한 객체
        Member member = new Member();
        member.setUserId(userId);
        member.setNickname(nickname);
        member.setUserPw(userPw);
        member.setUserName(userName);
        member.setTel(tel);
        member.setPostcode(postcode);
        member.setAddr1(addr1);
        member.setAddr2(addr2);

        // DB에 저장
        try {
            memberService.addItem(member);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        return restHelper.sendJson();
    }

    // 로그인
    @PostMapping("/api/member/login")
    public Map<String, Object> login(
            // 세션을 사용해야 하므로 request 객체가 필요하다
            // --> 
            HttpServletRequest request,
            @RequestParam("userId") String userId,
            @RequestParam("userPw") String userPw,
            @RequestParam(value = "rememberId", defaultValue = "N") String rememberId) {
        // 1) 입력값에 대한 유효성 검사
        // --- 생략 ---

        // 2) 입력값을 Beans 객체레 저장
        Member input = new Member();
        input.setUserId(userId);
        input.setUserPw(userPw);

        // 3) 로그인 시도
        Member output = null;

        try {
            output = memberService.login(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        if (rememberId.equals("Y")) {
            try {
                webHelper.writeCookie("rememberId", userId, 60 * 60 * 24 * 7, "localhost", "/login");
            } catch (Exception e) {
                return restHelper.serverError("아이디 저장 실패");
            }
        }

        // 프로필 사진의 경로의 URL로 변환
        output.setProfile(fileHelper.getUrl(output.getProfile()));

        // 4) 로그인에 성공했다면 회원 정보를 세션에 저장한다
        HttpSession session = request.getSession();
        session.setAttribute("memberInfo", output);

        // 5) 로그인이 처리되었음을 응답한다

        return restHelper.sendJson();
    }

    // 로그아웃 기능 
    @GetMapping("/api/member/logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return restHelper.sendJson();
    }

    // 아이디 찾기 
    @PostMapping("/api/member/findId")
    public Map<String, Object> findId(
        @RequestParam("user_name") String userName,
        @RequestParam("tel") String tel) {
        
        Member input = new Member();
        input.setUserName(userName);
        input.setTel(tel);

        Member output = null;

        try {
            output = memberService.findId(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("item", output.getUserId());

        return restHelper.sendJson(data);
    }
}
