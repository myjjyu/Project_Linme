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
import kr.project.linme.services.MemberService;

@RestController
public class MypageRestController {

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private MemberService memberService;


    /**
     * 닉네임 수정 API
     */
    @PutMapping("/api/myPage/nickname-update")
    public Map<String, Object> updateNickname(
            HttpServletRequest request,
            @SessionAttribute(value="memberInfo") Member memberInfo,
            @RequestParam("nickname") String nickname) {

        // 닉네임 중복 검사
        try {
            memberService.isUniqueNickname(nickname);
        } catch (Exception e) {
            return restHelper.badRequest(e); // 중복된 닉네임일 경우 에러 반환
        }

        // 닉네임 DB 업데이트
        memberInfo.setNickname(nickname);
        Member updatedMember;
        try {
            updatedMember = memberService.updateNickname(memberInfo);
        } catch (Exception e) {
            return restHelper.serverError(e); // 서버 에러 발생 시 처리
        }

        // 변경된 정보로 세션 갱신
        request.getSession().setAttribute("memberInfo", updatedMember);

        return restHelper.sendJson();
    }

    /**
     * 프로필 사진 수정 API
     */
    @PutMapping("/api/myPage/profile-update")
    public Map<String, Object> updateProfile(
            HttpServletRequest request,
            @SessionAttribute("memberInfo") Member memberInfo,
            @RequestParam(value = "delete_photo", defaultValue = "N") String deletePhoto,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {

        // 현재 프로필 사진 경로 가져오기
        String currentPhoto = memberInfo.getProfile();

        // 업로드된 파일 처리
        String savedFilePath = null;
        if (photo != null && !photo.isEmpty()) {
            try {
                savedFilePath = fileHelper.saveMultipartFile(photo).getFilePath();
            } catch (Exception e) {
                return restHelper.serverError("파일 저장 중 오류가 발생했습니다.");
            }
        }

        // 기존 사진 삭제 요청 처리
        if (currentPhoto != null && deletePhoto.equals("Y")) {
            fileHelper.deleteUploadFile(currentPhoto);
            currentPhoto = null; // 기존 사진 경로 초기화
        }

        // 새로 업로드된 파일 경로가 있다면 갱신
        if (savedFilePath != null) {
            currentPhoto = savedFilePath;
        }

        // DB 업데이트
        memberInfo.setProfile(currentPhoto);
        Member updatedMember;
        try {
            updatedMember = memberService.updateProfile(memberInfo);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        // 변경된 정보로 세션 갱신
        request.getSession().setAttribute("memberInfo", updatedMember);

        return restHelper.sendJson();
    }

}
