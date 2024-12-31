package kr.project.linme.controllers.apis;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.project.linme.helpers.FileHelper;
import kr.project.linme.helpers.RestHelper;
import kr.project.linme.models.Member;
import kr.project.linme.models.UploadItem;
import kr.project.linme.services.MemberService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;





@RestController
@Tag(name = "MyPage edit API", description = "내 정보 수정 API")
public class MypageRestController {

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private MemberService memberService;


    /* 닉네임 중복*/
    @GetMapping("/api/myPage/nickname_unique_check")
    @Operation(summary = "닉네임 중복검사", description = "닉네임 중복검사를 수행합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "사용 가능한 닉네임 입니다."),
        @ApiResponse(responseCode = "400", description = "사용 중인 닉네임입니다.")
    })
    @Parameters({
        @Parameter(name = "nickname", description = "닉네임", schema = @Schema(type = "string"), required = true)
    })
    public Map<String,Object>nicknameUniqueCheck(@RequestParam("nickname") String nickname) {
        try {
            memberService.isUniqueNickname(nickname);
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }
        return restHelper.sendJson();
    }
    
    /** 프로필 수정*/
    @PutMapping("/api/myPage/profile-update")
    @Operation(summary = "프로필 수정", description = "프로필사진과 닉네임을 변경합니다다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "프로필 변경에 성공하였습니다."),
        @ApiResponse(responseCode = "400", description = "프로필 변경경에 실패하였습니다.")
    })
    @Parameters({
        @Parameter(name = "user_id", description = "프로필사진", schema = @Schema(type = "string"), required = true),
        @Parameter(name = "nickname", description = "닉네임", schema = @Schema(type = "string"), required = true),
    })
    public Map<String, Object> updateProfile(
            HttpServletRequest request,
            @SessionAttribute("memberInfo") Member memberInfo,
                @RequestParam(value="nickname", required = false) String nickname,
                @RequestParam(value = "delete-profile", defaultValue = "N") String deleteProfile,
                @RequestParam(value = "profile", required = false) MultipartFile profile) {

        Member input=new Member();
        input.setNickname(nickname);
        input.setMemberId(memberInfo.getMemberId());

        if (nickname != null && !nickname.equals(memberInfo.getNickname())) {
            input.setNickname(nickname);
            try {
                memberService.isUniqueNickname(nickname);
            } catch (Exception e) {
                return restHelper.badRequest(e);
            }
        } else {
            input.setNickname(memberInfo.getNickname());
        }

        UploadItem uploadItem=null;

        try {
            if (profile != null && !profile.isEmpty()) {
                uploadItem = fileHelper.saveMultipartFile(profile);
            }
        } catch (Exception e) {
            return restHelper.serverError(e); // 파일 저장 실패 시 에러 반환
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

    /** 비밀번호 변경 */
    @PutMapping("/api/myPage/password-update")
    @Operation(summary = "비밀번호 변경", description = "비밀번호를 변경합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "수정되었습니다."),
        @ApiResponse(responseCode = "400", description = "비밀번호 변경에 실패하였습니다.")
    })
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
    @Operation(summary = "배송지 변경", description = "배송지를 변경합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "수정되었습니다."),
        @ApiResponse(responseCode = "400", description = "배송지 변경에 실패하였습니다.")
    })
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

        /** 회원 탈퇴 */
        @DeleteMapping("/api/myPage/out")
        @Operation(summary = "회원탈퇴", description = "회원탈퇴를 수행합니다.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "탈퇴되었습니다."),
            @ApiResponse(responseCode = "400", description = "탈퇴에 실패하였습니다.")
        })
        public Map<String, Object> out(
            HttpServletRequest request, 
            @SessionAttribute("memberInfo") Member memberInfo) {
            try {
                // 탈퇴 서비스 호출
                memberService.out(memberInfo);
            } catch (Exception e) {
                // 오류 발생 시 에러 반환
                return restHelper.serverError(e);
            }

            // 로그아웃 처리를 위해 세션 무효화
            HttpSession session = request.getSession();
            session.invalidate();

            // 성공 응답 반환
            return restHelper.sendJson();
        }

    
}
