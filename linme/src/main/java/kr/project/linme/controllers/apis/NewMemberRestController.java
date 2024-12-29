package kr.project.linme.controllers.apis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.project.linme.helpers.RestHelper;
import kr.project.linme.models.NewMember;
import kr.project.linme.services.NewMemberService;

@RestController
@Tag(name = "NewMember API", description = "신규 회원 가입 API")
public class NewMemberRestController {

    @Autowired
    private RestHelper restHelper;
    
    @Autowired
    private NewMemberService newMemberService;

    // 신규 회원 가입 API
    @GetMapping("/api/newMember")
    @Operation(
        summary = "주간/월간 신규 회원 조회",
        description = "주간/월간 신규 회원 조회합니다."
    )
    public Map<String, Object> newMemberList() {

        List<NewMember> newMemberListW;
        List<NewMember> newMemberListM;

        try {
            newMemberListW = newMemberService.getWeeklyNew();
            newMemberListM = newMemberService.getMonthlyNew();
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        data.put("weekly", newMemberListW);
        data.put("monthly", newMemberListM);

        return restHelper.sendJson(data);
    }
    
}
