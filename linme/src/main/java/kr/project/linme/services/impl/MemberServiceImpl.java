package kr.project.linme.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.Member2Mapper;
import kr.project.linme.models.Member;
import kr.project.linme.services.MemberService;

import lombok.extern.slf4j.Slf4j;

/*
* 회원 관리 기능에 대한 비즈니스 로직 처리를 담당하는 서비스 계층의 구현체
*/
@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
    //SQL문을 구현하고 있는 Mapper 객체 주입
    @Autowired
    private Member2Mapper memberMapper;

    //회원가입
    @Override
    public Member addItem(Member input) throws Exception{
        int rows=0;

        try {
            rows=memberMapper.insert(input);
            if(rows==0){
                throw new Exception("저장된 데이터가 없습니다.");
            }    
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.");
            throw e;
        }
    
        return memberMapper.selectItem(input);
    }
    // 로그인
    @Override
    public Member login(Member input) throws Exception {
        Member output=null;

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
            int rows=memberMapper.updateLoginDate(output);

            if(rows==0){
                throw new Exception("존재하지 않는 회원에 대한 요청입니다.");
            }
        } catch (Exception e) {
            log.error("Member 데이터 갱신에 실패했습니다.");
        }
        return output;
    }
    
    // 비밀번호 변경
    @Override
    public Member editPw(Member input) throws  Exception {
        int rows=0;
        try {
            rows=memberMapper.updatePw(input);
            //WHERE절 조건에 맞는 데이터가 없는 경우 --> 비밀번호 잘못됨
            if(rows==0){
                throw new Exception("현재 비밀번호를 확인하세요.");
            }
        } catch (Exception e) {
            log.error("Member 데이터 수정에 실패했습니다.", e);
            throw e;
        }
        return memberMapper.selectItem(input);
    }


    //닉네임 유효성 검사
    /*@Override  
    public void isUniqueNickname(String nickname) throws  Exception {
        Member input=new Member();
        input.setNickname(nickname);

        int output=0;
        try {
            output=memberMapper.selectCount(input);

            if(output>0){
                throw new Exception("사용 중인 닉네임입니다.");
            }
        } catch (Exception e) {
            log.error("닉네임 중복검사에 실패했습니다.",e);
            throw e;
        }
    }*/

    @Override  
    public void isUniqueNickname(String nickname) throws Exception {
        Member input = new Member();
        input.setNickname(nickname);

        int output = memberMapper.selectNickname(input);

        if (output > 0) {
            throw new Exception("사용 중인 닉네임입니다.");
        }
    }


    @Override
    public Member updateProfile(Member input) throws Exception {
        try {
            int rows = memberMapper.updateProfile(input); 
            if (rows == 0) {
                throw new Exception("프로필 사진 업데이트에 실패했습니다.");
            }
        } catch (Exception e) {
            log.error("프로필 사진 업데이트 실패", e);
            throw e;
        }
        // 업데이트된 회원 정보를 다시 조회하여 반환
        return memberMapper.selectItem(input);
    }

    @Override
    public Member updateNickname(Member input) throws Exception {
        // 닉네임 중복 검사
        isUniqueNickname(input.getNickname());

        try {
            int rows = memberMapper.updateNickname(input);
            if (rows == 0) {
                throw new Exception("닉네임 변경에 실패했습니다.");
            }
        } catch (Exception e) {
            log.error("닉네임 변경 실패", e);
            throw e;
        }

        return memberMapper.selectItem(input);
    }



}
