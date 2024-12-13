package kr.project.linme.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.models.Member;
import kr.project.linme.services.MemberService;
import kr.project.linme.exceptions.ServiceNoResultException;
import kr.project.linme.mappers.MemberMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    // 아이디(이메일) 중복검사
    @Override
    public void isUniqueUserId(String userId) throws Exception {
        Member input = new Member();
        input.setUserId(userId);

        int output = 0;

        try {
            output = memberMapper.selectCount(input);

            if (output > 0) {
                throw new Exception("사용할 수 없는 아이디(이메일) 입니다.");
            }
        } catch (Exception e) {
            log.error("아이디(이메일) 중복검사에 실패했습니다.", e);
            throw e;
        }
    }

    // 닉네임 중복검사
    @Override
    public void isUniqueNickname(String nickname) throws Exception {
        Member input = new Member();
        input.setNickname(nickname);

        int output = 0;

        try {
            output = memberMapper.selectCount(input);

            if (output > 0) {
                throw new Exception("사용할 수 없는 닉네임 입니다.");
            }
        } catch (Exception e) {
            log.error("닉네임 중복검사에 실패했습니다.", e);
            throw e;
        }
        
    }

    @Override 
    public Member addItem(Member input) throws ServiceNoResultException, Exception {
        int rows = memberMapper.insert(input);

        if (rows == 0) {
            throw new ServiceNoResultException("저장된 데이터가 없습니다.");
        }

        return memberMapper.selectItem(input);
    }   

    // 로그인
    @Override
    public Member login(Member input) throws Exception {
        Member output = null;

        try {
            output = memberMapper.login(input);

            if (output == null) {
                throw new Exception("아이디 혹은 비밀번호를 확인하세요.");
            }
        } catch (Exception e) {
            log.error("Member 데이터 조회에 실패했습니다.", e);
            throw e;
        }
        // 데이터 조회에 성공했다면 마지막 로그인 시각 갱신 
        try {
            int rows = memberMapper.updateLoginDate(output);

            if(rows == 0){
                throw new Exception("존재하지 않는 회원에 대한 요청입니다.");
            }
        } catch (Exception e) {
            log.error("Member 데이터 갱신에 실패했습니다.");
        }
        return output;
    }

    // 배송지 변경
    @Override
    public Member updatePostcode(Member input) throws Exception {
        int rows=0;
        try {
            rows = memberMapper.updatePostcode(input); 
            if (rows == 0) {
                throw new Exception("배송지 변경에 실패했습니다.");
            }
        } catch (Exception e) {
            log.error("배송지 변경 실패", e);
            throw e;
        }
        // 업데이트된 회원 정보를 다시 조회하여 반환
        return memberMapper.selectItem(input);
    }
    

    // 비밀번호 변경
    @Override
    public Member editPw(Member input) throws Exception {
        int rows=0;
    
        try {
            rows=memberMapper.updatePw(input);
            if(rows==0){
                throw new Exception("현재 비밀번호를 확인하세요.");
            }
        } catch (Exception e) {
            log.error("비밀번호 수정에 실패했습니다.", e);
            throw e;
        }
        return memberMapper.selectItem(input);
    }

    // 프로필 정보 변경
    @Override
    public Member updateProfile(Member input) throws Exception {
        int rows=0;
    
        try {
            rows=memberMapper.updateProfile(input);
            if(rows==0){
                throw new Exception("프로필 변경 실패");
            }
        } catch (Exception e) {
            log.error("프로필 수정에 실패했습니다.", e);
            throw e;
        }
        return memberMapper.selectItem(input);
    }

    //회원 탈퇴
    @Override
    public int out(Member input) throws Exception {
        int rows = 0;

        try {
            // 단순히 회원 삭제를 위한 Mapper 호출
            rows = memberMapper.out(input);

            if (rows == 0) {
                throw new Exception("존재하지 않는 회원에 대한 요청입니다.");
            }
        } catch (Exception e) {
            log.error("Member 데이터 삭제에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }


    // 탈퇴회원관리 
    @Override
    public List<Member>processOutMembers()throws Exception{
        List<Member>output=null;

        try {
            //1) is_out이 Y인 상태로 특정 시간이 지난 데이터를 조회한다.
            output=memberMapper.selectOutMembersPhoto();

            //2) 탈퇴 요청된 데이터를 삭제한다.
            memberMapper.deleteOutMembers();
        } catch (Exception e) {
            throw new Exception("탈퇴 처리에 실패했습니다.");
        }
        return output;
    }

    // 아이디 찾기
    @Override
    public Member findId(Member input) throws Exception {
        Member output = null;

        try {
            output = memberMapper.findId(input);

            if (output == null) {
                throw new Exception("Member 조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("아이디 검색에 실패했습니다", e);
            throw e;
        }

        return output;
    }

    @Override
    public void resetPw(Member input) throws Exception {
        int rows = 0;

        try {
            rows = memberMapper.resetPw(input);

            if (rows == 0) {
                throw new Exception("존재하지 않는 회원에 대한 요청입니다.");
            }
        } catch (Exception e) {
            log.error("비밀번호 업데이트 실패", e);
            throw e;
        }
    }
}
   