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
    @Insert("INSERT INTO member (" +
            "user_id, user_pw, user_name, nickname, " +
            "tel, postcode, addr1, addr2, addr_name, " +
            "addr_msg, profile, is_out, is_admin, login_date, " +
            "reg_date, edit_date) " +
            "VALUES (#{userId}, #{userPw}, #{userName}, #{nickname}, " +
            "#{tel}, #{postcode}, #{addr1}, #{addr2}, #{addrName}, " +
            "#{addrMsg}, #{profile}, 'N', 'N', null, " +
            "now(), now())")
    @Options(useGeneratedKeys = true, keyProperty = "memberId", keyColumn = "member_id")
    public int insert(Member input);

    // 회원 정보 수정
    // user_id = 이메일
    @Update("UPDATE member SET " +
            "user_id = #{userId}, " +
            "user_pw = #{userPw}, " +
            "user_name = #{userName}, " +
            "nickname = #{nickname}, " +
            "tel = #{tel}, " +
            "postcode = #{postcode}, " +
            "addr1 = #{addr1}, " +
            "addr2 = #{addr2}, " +
            "addr_name = #{addrName}, " +
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
    @Select("SELECT member_id, user_id, user_pw, user_name, nickname, tel, postcode, addr1, addr2, addr_name, addr_msg, profile, is_out, is_admin, login_date, reg_date, edit_date FROM member WHERE member_id = #{memberId}")
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
        @Result(property = "addrName", column = "addr_name"),
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
    @Select("SELECT member_id, user_id, user_pw, user_name, nickname, tel, postcode, addr1, addr2, addr_name, addr_msg, profile, is_out, is_admin, login_date, reg_date, edit_date FROM member")
    @ResultMap("memberMap")
    public List<Member> selectList(Member input);

    // 회원 수 조회
    @Select("SELECT COUNT(*) FROM member")
    public int selectCount(Member input);
}

