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

import kr.project.linme.models.NewMember;

@Mapper
public interface NewMemberMapper {
    // 집계 데이터 추가
    @Insert("INSERT INTO new_member (member_count, reg_date) " +
            "SELECT DATE(reg_date) AS dt, COUNT(*) AS cnt " +
            "FROM member " +
            "WHERE DATE(reg_date) = DATE(DATE_ADD(NOW(), INTERVAL -1 DAY)) " +
            "GROUP BY dt")
    @Options(useGeneratedKeys = true, keyProperty = "newMemberId", keyColumn = "new_member_id")
    public int insert();

    @Update("...")
    public int update(NewMember input);

    // 31일이 지난 집계 데이터 삭제
    @Delete("DELETE FROM new_member " +
            "WHERE DATE(reg_date) < DATE(DATE_ADD(NOW(), INTERVAL -31 DAY))")
    public int delete();

    @Select("SELECT " + 
                "new_member_id, " + 
                "member_count, " + 
                "reg_date " + 
            "FROM new_member " + 
            "WHERE new_member_id=#{newMemberId}")
    @Results(id="resultMap", value={
        @Result(property="newMemberId", column="new_member_id"),
        @Result(property="memberCount", column="member_count"),
        @Result(property="regDate", column="reg_date")
    })
    public NewMember selectItem(NewMember input);

    // 주간 회원 집계 데이터 조회
    @Select("SELECT " + 
                "new_member_id, " + 
                "member_count, " + 
                "reg_date " + 
            "FROM new_member " + 
            "ORDER BY reg_date DESC " + 
            "LIMIT 7")
    @ResultMap("resultMap")
    public List<NewMember> selectWeeklyNew();

    // 월간 회원 집계 데이터 조회(4주간)
    @Select("SELECT " + 
                "CONCAT(DATE_FORMAT(MIN(reg_date), '%Y-%m-%d'), ' ~ ', DATE_FORMAT(MAX(reg_date), '%Y-%m-%d')) AS reg_date, " +
                "SUM(member_count) AS member_count " +
            "FROM new_member " +
            "GROUP BY FLOOR(DATEDIFF(DATE_ADD(NOW(), INTERVAL -1 DAY), reg_date) / 7) " + // 주 단위로 그룹화화
            "ORDER BY MIN(reg_date) DESC " +
            "LIMIT 0, 4")
    @ResultMap("resultMap")
    public List<NewMember> selectMonthlyNew();

    @Select("...")
    public int selectCount(NewMember input);
}
