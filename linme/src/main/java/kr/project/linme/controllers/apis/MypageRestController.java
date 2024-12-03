package kr.project.linme.controllers.apis;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import kr.project.linme.helpers.FileHelper;
import kr.project.linme.helpers.RestHelper;
import kr.project.linme.models.Member;
import kr.project.linme.models.UploadItem;
import kr.project.linme.services.MemberService;
import org.springframework.web.bind.annotation.GetMapping;





@RestController
public class MypageRestController {

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private MemberService memberService;


    // /**
    //  * 닉네임 수정 API
    //  */
    // @PutMapping("/api/myPage/nickname-update")
    // public Map<String, Object> updateNickname(
    //         HttpServletRequest request,
    //         @SessionAttribute(value = "memberInfo") Member memberInfo,
    //         @RequestParam("nickname") String nickname) {

    //     // 닉네임 중복 검사
    //     try {
    //         memberService.isUniqueNickname(nickname);
    //     } catch (Exception e) {
    //         return restHelper.badRequest(e); // 중복된 닉네임일 경우 에러 반환
    //     }

    //     // 닉네임 DB 업데이트
    //     memberInfo.setNickname(nickname);
    //     Member updatedMember;
    //     try {
    //         updatedMember = memberService.updateNickname(memberInfo);
    //     } catch (Exception e) {
    //         return restHelper.serverError(e); // 서버 에러 발생 시 처리
    //     }

    //     // 변경된 정보로 세션 갱신
    //     request.getSession().setAttribute("memberInfo", updatedMember);

    //     return restHelper.sendJson();
    // }

    // /**
    //  * 프로필 사진 수정 API
    //  */
    // @PutMapping("/api/myPage/profile-update")
    // public Map<String, Object> updateProfile(
    //         HttpServletRequest request,
    //         @SessionAttribute("memberInfo") Member memberInfo,
    //         @RequestParam(value = "delete_profile", defaultValue = "N") String deleteProfile,
    //         @RequestParam(value = "profile", required = false) MultipartFile profile) {

    //     // 현재 프로필 사진 경로 가져오기
    //     String currentProfile = memberInfo.getProfile();

    //     // 업로드된 파일 처리
    //     String savedFilePath = null;
    //     if (profile != null && !profile.isEmpty()) {
    //         try {
    //             savedFilePath = fileHelper.saveMultipartFile(profile).getFilePath();
    //         } catch (Exception e) {
    //             return restHelper.serverError("파일 저장 중 오류가 발생했습니다.");
    //         }
    //     }

    //     // 기존 사진 삭제 요청 처리
    //     if (currentProfile != null && deleteProfile.equals("Y")) {
    //         fileHelper.deleteUploadFile(currentProfile);
    //         currentProfile = null; // 기존 사진 경로 초기화
    //     }

    //     // 새로 업로드된 파일 경로가 있다면 갱신
    //     if (savedFilePath != null) {
    //         currentProfile = savedFilePath;
    //     }

    //     // DB 업데이트
    //     memberInfo.setProfile(currentProfile);
    //     Member updatedMember;
    //     try {
    //         updatedMember = memberService.updateProfile(memberInfo);
    //     } catch (Exception e) {
    //         return restHelper.serverError(e);
    //     }

    //     // 변경된 정보로 세션 갱신
    //     request.getSession().setAttribute("memberInfo", updatedMember);

    //     return restHelper.sendJson();
    // }

    /* 닉네임 중복*/
    @GetMapping("/api/myPage/nickname_unique_check")
    public Map<String,Object>nicknameUniqueCheck(@RequestParam("nickname") String nickname) {
        try {
            memberService.isUniqueNickname(nickname);
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }
        return restHelper.sendJson();
    }
    
 /**
     * 프로필 수정
     */
    @PutMapping("/api/myPage/profile-update")
    public Map<String, Object> updateProfile(
            HttpServletRequest request,
            @SessionAttribute("memberInfo") Member memberInfo,
                @RequestParam("nickname") String nickname,
                @RequestParam(value = "delete_profile", defaultValue = "N") String deleteProfile,
                @RequestParam(value = "profile", required = false) MultipartFile profile) {

        Member input=new Member();
        input.setNickname(nickname);
        input.setMemberId(memberInfo.getMemberId());

        try {
            memberService.isUniqueNickname(nickname);
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }

        UploadItem uploadItem=null;

        try {
            uploadItem=fileHelper.saveMultipartFile(profile);
        } catch (NullPointerException e) {

        }catch(Exception e){
            return restHelper.serverError(e);
        }

        Member member=new Member();
        member.setMemberId(memberInfo.getMemberId());
        member.setNickname(nickname);
    
        // 현재 프로필 사진 경로 가져오기
        String currentProfile = memberInfo.getProfile();

        //현재 프로필 사진이 있는 경우
        if(currentProfile!=null&&!currentProfile.equals("")){
            //로그인 시에 DB에서 가져온 이미지 파일 경로에 "/files"를 붙여 놨으므로,
            // 이 값을 제거해야 한다.
            currentProfile=fileHelper.getFilePath(currentProfile);

            //기존 사진의 삭제가 요청되었다면?
            if(deleteProfile.equals("Y")){
                fileHelper.deleteUploadFile(currentProfile);

                //업로드 된 사진이 있다면 Beans에 포함한다.
                //기존 파일이 있을 경우에는 삭제없이는 정보를 갱신하면 안된다.
                if(uploadItem!=null){
                    member.setProfile(uploadItem.getFilePath());
                }else{
                    //삭제만 하고 새로운 파일은 업로드 하지 않은 경우
                    // --> Member 클래스에서 photo는 String
                    // --> 기본값이 null 이란 의미
                    // --> 별도로 처리하지 않는 한 member객체의 photo는 null이란 의미
                    member.setProfile(null);
                }
            }else{
                //삭제 요청이 없는 경우는 세션의 사진경로(=기존경로)를 그대로 적용하여
                //기존 사진을 유지하도록 한다.
                member.setProfile(currentProfile);
            }
        }else{
            // 업로드 된 사진이 있다면 Beans에 포함한다.
            if(uploadItem!=null){
                member.setProfile(uploadItem.getFilePath());
            }
        }
        /** 5) DB에 저장 */
        Member output=null;

        try {
            output=memberService.updateProfile(member);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }
        // 프로필 사진의 경로를 URL로 변환
        output.setProfile(fileHelper.getUrl(output.getProfile()));

        /** 6) 변경된 정보로 세션 갱신 */
        request.getSession().setAttribute("memberInfo", output);
     
        return restHelper.sendJson();
    
    
    }

    
        
    
    

    


    /**
     * 비밀번호 확인 API
     */
    // @PutMapping("/api/myPage/password-check")
    // public Map<String, Object> checkPassword(
    //         @SessionAttribute("memberInfo") Member memberInfo,
    //         @RequestParam("user_pw") String userPw) {

    //     // 입력된 비밀번호로 Member 객체 생성
    //     Member input = new Member();
    //     input.setMemberId(memberInfo.getMemberId());
    //     input.setUserPw(userPw);

    //     // 비밀번호 확인 로직
    //     try {
    //         boolean isValid = memberService.checkPassword(input);

    //         if (!isValid) {
    //             // 비밀번호가 일치하지 않을 경우 에러 반환
    //             return restHelper.badRequest("현재 비밀번호가 올바르지 않습니다.");
    //         }
    //     } catch (Exception e) {
    //         // 서버 에러 처리
    //         return restHelper.serverError("비밀번호 확인 중 오류가 발생했습니다.");
    //     }

    //     // 비밀번호가 일치하는 경우 성공 응답 반환
    //     return restHelper.sendJson();
    // }


    /** 비밀번호 변경 */
    @PutMapping("/api/myPage/password-update")
    public Map<String, Object> updatePassword(
            HttpServletRequest request,
            @SessionAttribute("memberInfo") Member memberInfo,
                @RequestParam("user_pw") String userPw,
                @RequestParam("new_user_pw") String newUserPw) {

        Member member = new Member();
        member.setMemberId(memberInfo.getMemberId());
        member.setUserPw(userPw);
        member.setNewUserPw(newUserPw);

        Member output=null;

        try {
            output = memberService.editPw(member);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        // 세션 갱신
        request.getSession().setAttribute("memberInfo", output);

        return restHelper.sendJson();
    }


   // 배송지 변경
    @PutMapping("/api/myPage/postcode-update")
    public Map<String, Object> updatePostcode(
            HttpServletRequest request, // 세션 갱신용
            @SessionAttribute("memberInfo") Member memberInfo, // 현재 세션 정보 확인용
                @RequestParam("postcode") String postcode,
                @RequestParam("addr1") String addr1,
                @RequestParam("addr2") String addr2,
                @RequestParam("addrMsg") String addrMsg){ // 배송 요청사항

        Member member = new Member();
        member.setMemberId(memberInfo.getMemberId());
        member.setPostcode(postcode);
        member.setAddr1(addr1);
        member.setAddr2(addr2);
        member.setAddrMsg(addrMsg);

        
        /**  DB에 저장 */
        Member output=null;

        try {
            output=memberService.updatePostcode(member);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        
        /** 변경된 정보로 세션 갱신 */
        request.getSession().setAttribute("memberInfo", output);

        return restHelper.sendJson();

        }
    
}
