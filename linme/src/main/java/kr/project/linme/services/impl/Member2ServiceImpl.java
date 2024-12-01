// package kr.project.linme.services.impl;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import kr.project.linme.mappers.Member2Mapper;
// import kr.project.linme.models.Member;
// import kr.project.linme.services.Member2Service;

// import lombok.extern.slf4j.Slf4j;

// /*
// * 회원 관리 기능에 대한 비즈니스 로직 처리를 담당하는 서비스 계층의 구현체
// */
// @Slf4j
// @Service
// public class Member2ServiceImpl implements Member2Service{
//     //SQL문을 구현하고 있는 Mapper 객체 주입
//     @Autowired
//     private Member2Mapper memberMapper;

//     // // 비밀번호 변경
//     // @Override
//     // public Member editPw(Member input) throws Exception {
//     //     int rows=0;
    
//     //     try {
//     //         rows=memberMapper.updatePw(input);
//     //         if(rows==0){
//     //             throw new Exception("현재 비밀번호를 확인하세요.");
//     //         }
//     //     } catch (Exception e) {
//     //         log.error("비밀번호 수정에 실패했습니다.", e);
//     //         throw e;
//     //     }
//     //     return memberMapper.selectItem(input);
//     //     // if (validPassword == 0) {
//     //     //     // 비밀번호가 일치하지 않을 경우 예외 발생
//     //     //     throw new Exception("현재 비밀번호가 올바르지 않습니다.");
//     //     // }
    
//     //     // // 비밀번호 업데이트
//     //     // int updatedRows = memberMapper.updatePw(input);
//     //     // if (updatedRows == 0) {
//     //     //     // 업데이트 실패 시 예외 발생
//     //     //     throw new Exception("비밀번호 업데이트에 실패했습니다.");
//     //     // }
    
//     //     // // 업데이트된 사용자 정보 조회 및 반환
//     //     // return memberMapper.selectItem(input);
//     // }

//     // 입력한 비밀번호와 DB 비밀번호가 일치한지 확인
//     // @Override
//     // public boolean checkPassword(Member input) throws Exception {
//     //     int count = memberMapper.countByPassword(input);
//     //     return count > 0; // 비밀번호가 일치하면 true, 아니면 false 반환
//     // }
    

//     // // 닉네임 중복 검사
//     // @Override  
//     // public void isUniqueNickname(Member input) throws Exception {
//     //     int output=0;
//     //     try {
//     //         output=memberMapper.selectCount(input);

//     //         if(output>0){
//     //             throw new Exception("사용 중인 닉네임입니다.");
//     //         }
//     //     } catch (Exception e) {
//     //         log.error("닉네임 중복검사에 실패했습니다.", e);
//     //         throw e;
//     //     }
//     // }


//     // 프로필 변경
//     @Override
//     public Member updateProfile(Member input) throws Exception {
//         try {
//             int rows = memberMapper.updateProfile(input); 
//             if (rows == 0) {
//                 throw new Exception("프로필 사진 업데이트에 실패했습니다.");
//             }
//         } catch (Exception e) {
//             log.error("프로필 사진 업데이트 실패", e);
//             throw e;
//         }
//         // 업데이트된 회원 정보를 다시 조회하여 반환
//         return memberMapper.selectItem(input);
//     }

//     // 닉네임 변경
//     // @Override
//     // public Member updateNickname(Member input) throws Exception {
        
//     //     isUniqueNickname(input.getNickname());

//     //     try {
//     //         int rows = memberMapper.updateNickname(input);
//     //         if (rows == 0) {
//     //             throw new Exception("닉네임 변경에 실패했습니다.");
//     //         }
//     //     } catch (Exception e) {
//     //         log.error("닉네임 변경 실패", e);
//     //         throw e;
//     //     }

//     //     return memberMapper.selectItem(input);
//     // }


//     // // 배송지 변경
//     // @Override
//     // public Member updatePostcode(Member input) throws Exception {
//     //     int rows=0;
//     //     try {
//     //         rows = memberMapper.updatePostcode(input); 
//     //         if (rows == 0) {
//     //             throw new Exception("배송지 변경에 실패했습니다.");
//     //         }
//     //     } catch (Exception e) {
//     //         log.error("배송지 변경 실패", e);
//     //         throw e;
//     //     }
//     //     // 업데이트된 회원 정보를 다시 조회하여 반환
//     //     return memberMapper.selectItem(input);
//     // }


//     @Override
//     public List<Member>processOutMembers()throws Exception{
//         List<Member>output=null;

//         try {
//             //1) is_out이 Y인 상태로 특정 시간이 지난 데이터를 조회한다.
//             output=memberMapper.selectOutMembersPhoto();

//             //2) 탈퇴 요청된 데이터를 삭제한다.
//             memberMapper.deleteOutMembers();
//         } catch (Exception e) {
//             throw new Exception("탈퇴 처리에 실패했습니다.");
//         }
//         return output;
//     }

// }
