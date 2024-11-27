package kr.project.linme.controllers.apis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.project.linme.helpers.RestHelper;
import kr.project.linme.models.Member;
import kr.project.linme.services.MemberService;

@RestController
public class MemberRestController {

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private MemberService memberService;

    // 아이디 (이메일) 중복검사
    @GetMapping("/api/account/id_unique_checkk")
    public Map<String, Object> idUniqueCheck(@RequestParam("user_id") String userId) {
        try {
            memberService.isUniqueUserId(userId);
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }
        
        return restHelper.sendJson();
    }

    // 닉네임 중복검사
    @GetMapping("/api/account/nickname_unique_check")
    public Map<String, Object> nicknameUniqueCheck(@RequestParam("nickname") String nickname) {
        try {
            memberService.isUniqueNickname(nickname);
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }
        
        return restHelper.sendJson();
    }

    // 회원가입
    @PostMapping("/api/account/join")
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

}
