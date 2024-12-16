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
import kr.project.linme.exceptions.StringFormatException;
import kr.project.linme.helpers.FileHelper;
import kr.project.linme.helpers.RestHelper;
import kr.project.linme.helpers.WebHelper;
import kr.project.linme.helpers.RegexHelper;
import kr.project.linme.models.Member;
import kr.project.linme.services.MemberService;

@RestController
public class MemberRestController {

    @Autowired
    private RegexHelper regexHelper;

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private MemberService memberService;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private WebHelper webHelper;

    // 아이디 (이메일) 중복검사
    @GetMapping("/api/member/id_unique_check")
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
        @RequestParam("pwCheck") String pwCheck,
        @RequestParam("userName") String userName,
        @RequestParam("tel") String tel,
        @RequestParam("postcode") String postcode,
        @RequestParam("addr1") String addr1,
        @RequestParam("addr2") String addr2
    ) {

        try {
        // 아이디(이메일) 유효성 검사
        regexHelper.isValue(userId, "아이디(이메일)를 입력하세요.");
        regexHelper.isEmail(userId, "아이디(이메일)가 잘못되었습니다.");

        // 닉네임 유효성 검사
        regexHelper.isValue(nickname, "닉네임을 입력하세요.");
        regexHelper.isnickName(nickname, "닉네임은 한글, 영문, 숫자로 입력하세요.");

        // 비밀번호 유효성 검사
        regexHelper.isValue(userPw, "비밀번호를 입력하세요");
        regexHelper.ispwLinme(userPw, "비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상으로 입력하세요.");
        regexHelper.isMatch(userPw, pwCheck, "비밀번호 확인이 잘못되었습니다.");

        // 회원이름 유효성 검사
        regexHelper.isValue(userName, "회원이름을 입력하세요.");
        regexHelper.isKor(userName, "회원이름은 한글로만 입력하세요.");

        // 전화번호 유효성 검사
        regexHelper.isValue(tel, "전화번호를 입력하세요.");
        regexHelper.isPhone(tel, "전화번호가 잘못되었습니다.");

        // 우편번호 유효성 검사
        regexHelper.isValue(addr1, "주소를 입력하세요.");
        regexHelper.isValue(addr2, "상세주소를 입력하세요.");
        
    } catch (StringFormatException e) {
        // 유효성 검사 실패 시 에러 응답 반환
        return restHelper.badRequest(e);
    }

        try {
            // 아이디 중복 검사
            memberService.isUniqueUserId(userId);
        } catch (Exception e) {
            // 아이디 중복 시 에러 응답 반환
            return restHelper.badRequest(e);
        }

        try {
            // 닉네임 중복 검사
            memberService.isUniqueNickname(nickname);
        } catch (Exception e) {
            // 닉네임 중복 시 에러 응답 반환
            return restHelper.badRequest(e);
        }

        // 회원 정보 설정
        Member member = new Member();
        member.setUserId(userId);
        member.setNickname(nickname);
        member.setUserPw(userPw);
        member.setUserName(userName);
        member.setTel(tel);
        member.setPostcode(postcode);
        member.setAddr1(addr1);
        member.setAddr2(addr2);

        try {
            // 회원 정보 저장
            memberService.addItem(member);
        } catch (Exception e) {
            // 회원 정보 저장 실패 시 서버 에러 응답 반환
            return restHelper.serverError(e);
        }

        // 성공 응답 반환
        return restHelper.sendJson();
    }

    // 로그인
    @PostMapping("/api/member/login")
    public Map<String, Object> login(

            HttpServletRequest request,
            @RequestParam("userId") String userId,
            @RequestParam("userPw") String userPw,
            @RequestParam(value = "rememberId", defaultValue = "N") String rememberId) {
                

        try {
            // 아이디(이메일) 유효성 검사
            regexHelper.isValue(userId, "아이디(이메일)를 입력하세요.");
            regexHelper.isEmail(userId, "아이디(이메일) 형식이 올바르지 않습니다.");

            // 비밀번호 유효성 검사
            regexHelper.isValue(userPw, "비밀번호를 입력하세요.");
            regexHelper.ispwLinme(userPw, "비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상으로 입력하세요.");

        } catch (StringFormatException e) {
            // 유효성 검사 실패 시 에러 응답 반환
            return restHelper.badRequest(e);
        }

        // 로그인 시도할 회원 정보 설정
        Member input = new Member();
        input.setUserId(userId);
        input.setUserPw(userPw);

        Member output = null;

        try {
            // 로그인 처리
            output = memberService.login(input);
        } catch (Exception e) {
            // 로그인 실패 시 에러 응답 반환
            return restHelper.serverError(e);
        }

        // 아이디 저장을 위한 쿠키 설정
        if (rememberId.equals("Y")) {
            try {
                // 쿠키에 아이디 저장 (유효기간: 7일)
                webHelper.writeCookie("rememberId", userId, 60 * 60 * 24 * 7, "localhost", "/login");
            } catch (Exception e) {
                // 쿠키 저장 실패 시 서버 에러 응답 반환
                return restHelper.serverError("아이디 저장 실패");
            }
        }

        // 프로필 사진의 경로의 URL로 변환
        output.setProfile(fileHelper.getUrl(output.getProfile()));

        // 세션에 회원 정보 저장
        HttpSession session = request.getSession();
        session.setAttribute("memberInfo", output);

        // 로그인 성공 응답 반환
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
        
        // Member 객체 생성 
        Member input = new Member();
        input.setUserName(userName);
        input.setTel(tel);

        Member output = null;

        try {
            // 입력된 정보로 아이디 찾기
            output = memberService.findId(input);
        } catch (Exception e) {
            // 아이디 찾기 실패 시 서버 에러 응답 반환
            return restHelper.serverError(e);
        }

        // 응답할 데이터 설정
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("item", output.getUserId());

        //성공 응답 반환
        return restHelper.sendJson(data);
    }
}
