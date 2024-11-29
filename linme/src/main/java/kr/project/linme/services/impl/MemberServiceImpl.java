package kr.project.linme.services.impl;

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
}
   