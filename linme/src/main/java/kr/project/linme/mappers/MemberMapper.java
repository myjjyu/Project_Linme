package kr.project.linme.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.project.linme.models.Member;

@Mapper
public interface MemberMapper {

    // 회원 정보 삽입
    // 회원 가입시 필요한 정보들만 제외 하고는 N
    // 인증 칸을 나중에 번호로 직접 넣어서 정보 전달하게 수정 (완료)
    @Insert("INSERT INTO member (" +
            "user_id, user_pw, user_name, nickname, " +
            "tel, postcode, addr1, addr2, " +
            "addr_msg, profile, is_out, is_admin, login_date, " +
            "reg_date, edit_date) " +
            "VALUES (#{userId}, MD5(#{userPw}), #{userName}, #{nickname}, " +
            "#{tel}, #{postcode}, #{addr1}, #{addr2}, " +
            "'부재 시 문 앞에 놓아주세요', null, 'N', 'N', null, " +
            "now(), now())")
    @Options(useGeneratedKeys = true, keyProperty = "memberId", keyColumn = "member_id")
    public int insert(Member input);

    // 회원 정보 삽입
    // @Insert("INSERT INTO member (" +
    //         "user_id, user_pw, user_name, nickname, " +
    //         "tel, postcode, addr1, addr2, addr_name, " +
    //         "addr_msg, profile, is_out, is_admin, login_date, " +
    //         "reg_date, edit_date) " +
    //         "VALUES (#{userId}, #{userPw}, #{userName}, #{nickname}, " +
    //         "#{tel}, #{postcode}, #{addr1}, #{addr2}, #{addrName}, " +
    //         "#{addrMsg}, #{profile}, 'N', 'N', null, " +
    //         "now(), now())")
    // @Options(useGeneratedKeys = true, keyProperty = "memberId", keyColumn = "member_id")
    // public int insert(Member input);

    // 회원 정보 수정
    // user_id = 이메일
    @Update("UPDATE member SET " +
            "user_id = #{userId}, " +
            "user_pw = MD5(#{userPw}), " +
            "user_name = #{userName}, " +
            "nickname = #{nickname}, " +
            "tel = #{tel}, " +
            "postcode = #{postcode}, " +
            "addr1 = #{addr1}, " +
            "addr2 = #{addr2}, " +
            "addr_msg = #{addrMsg}, " +
            "profile = #{profile}, " +
            // "is_out = #{isOut}, " +
            // "is_admin = #{isAdmin}, " +
            // "login_date = #{loginDate}, " +
            "edit_date = NOW() " +
            "WHERE member_id = #{memberId}")
    public int update(Member input);

    // 회원 정보 삭제
    @Delete("DELETE FROM member WHERE member_id = #{memberId}")
    public int delete(Member input);

    // 단일 회원 정보 조회
    @Select("SELECT member_id, user_id, user_pw, user_name, nickname, tel, postcode, addr1, addr2, addr_msg, profile, is_out, is_admin, login_date, reg_date, edit_date FROM member WHERE member_id = #{memberId}")
    @Results(id = "memberMap", value = {
        @Result(property = "memberId", column = "member_id"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "userPw", column = "user_pw"),
        @Result(property = "userName", column = "user_name"),
        @Result(property = "nickname", column = "nickname"),
        @Result(property = "tel", column = "tel"),
        @Result(property = "postcode", column = "postcode"),
        @Result(property = "addr1", column = "addr1"),
        @Result(property = "addr2", column = "addr2"),
        @Result(property = "addrMsg", column = "addr_msg"),
        @Result(property = "profile", column = "profile"),
        @Result(property = "isOut", column = "is_out"),
        @Result(property = "isAdmin", column = "is_admin"),
        @Result(property = "loginDate", column = "login_date"),
        @Result(property = "regDate", column = "reg_date"),
        @Result(property = "editDate", column = "edit_date")
    })
    public Member selectItem(Member input);

    // 회원 다중 조회
    @Select("SELECT member_id, user_id, user_pw, user_name, nickname, tel, postcode, addr1, addr2, addr_msg, profile, is_out, is_admin, login_date, reg_date, edit_date FROM member")
    @ResultMap("memberMap")
    public List<Member> selectList(Member input);

    // 회원가입 아이디(이메일) , 닉네임 중복 체크
    @Select("<script>" + //
            "SELECT COUNT(*) FROM member" + //
            "<where>\n" + //
            "<if test='userId != null'>user_id = #{userId}</if>" + // 사용할수없는 이메일 입니다
            "<if test='nickname != null'>nickname = #{nickname}</if>" + // 사용할수없는 닉네임 입니다
        //     "<if test='member_id != 0'>AND member_id != #{memberId}</if>" +
            "</where>" +
            "</script>")
    public int selectCount(Member input);

    // 로그인
    @Select("SELECT " +
            "member_id, user_id, user_pw, user_name, nickname, " +
            "tel, postcode, addr1, addr2, profile, " +
            "addr_msg, is_out, is_admin, login_date, " +
            "reg_date, edit_date " +
            "FROM member " +
            "WHERE user_id = #{userId} AND user_pw = MD5(#{userPw}) AND is_out='N'")
    @ResultMap("memberMap")
    public Member login(Member input);

    //로그인 시간 업데이트
    @Update("UPDATE member SET login_date = NOW() WHERE member_id = #{memberId} AND is_out='N'")
    public int updateLoginDate(Member input);


        // 배송지 변경
        @Update("<script> "+
                "UPDATE member " +
                        "SET " +
                        "postcode=#{postcode}, " +
                        "addr1=#{addr1}, "+
                        "addr2=#{addr2}, "+
                        "addr_msg=#{addrMsg}, "+
                        // "is_out = #{isOut}, " +
                        // "is_admin = #{isAdmin}, " +
                        // "login_date = #{loginDate}, " +
                        "edit_date = NOW() " +
                        "WHERE member_id = #{memberId} "+
                "</script> ")
        public int updatePostcode(Member input);

        // 비밀번호 변경 
        @Update("<script> "+
                "UPDATE member " +
                        "SET "+
                        "user_pw=MD5(#{newUserPw}) "+
                        "WHERE member_id= #{memberId} AND user_pw=MD5(#{userPw}) "+
                "</script> ")
        public int updatePw(Member input);

        // 프로필 정보 변경
        @Update("<script> "+
                "Update member SET "+
                        "nickname =#{nickname}, "+
                        "profile = #{profile}, "+
                        // "is_out = #{isOut}, " +
                        // "is_admin = #{isAdmin}, " +
                                // "login_date = #{loginDate}, " +
                        "edit_date = NOW() " +
                        "WHERE member_id = #{memberId} "+
                "</script>")
        public int updateProfile(Member input);
        

        @Select("SELECT profile FROM member \n"+ //
                "WHERE is_out='Y' AND \n"+//
                "edit_date<DATE_ADD(NOW(),interval -1 minute) AND \n"+ //
                "profile IS NOT NULL ")
        @ResultMap("memberMap")
        public List<Member>selectOutMembersPhoto();


        @Delete("DELETE FROM member \n"+//
                "WHERE is_out='Y' AND \n "+//
                "edit_date <DATE_ADD(NOW(),interval -1 minute)")
        public int deleteOutMembers();



        //회원 탈퇴
        @Update("UPDATE member\n "+//
                "SET is_out='Y', edit_date=NOW()\n "+//
                "WHERE member_id = #{memberId} AND is_out='N'")
        public int out(Member input);

        // 아이디 찾기
        @Select("SELECT user_id FROM member " + //
            "WHERE user_name = #{userName} AND tel = #{tel} AND is_out ='N'")
        @ResultMap("memberMap")
        public Member findId(Member input);

        // 비밀번호 재발급
        @Update("UPDATE member SET " +
            "user_pw = MD5(#{userPw}) " +
            "WHERE user_name = #{userName} AND tel = #{tel} AND user_id = #{userId} AND is_out='N'")
        public int resetPw(Member input);
}