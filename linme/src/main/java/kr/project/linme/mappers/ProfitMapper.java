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

import kr.project.linme.models.Profit;

@Mapper
public interface ProfitMapper {

    @Insert(
            "INSERT INTO profit (category_id, total_count, reg_date) " + 
            "SELECT " +
                "p.category_id, " +                   // 카테고리 일련번호
                "SUM(o.order_count) AS total_count, " + // 총 판매량
                "DATE_FORMAT(o.reg_date, '%Y-%m-%d') AS reg_date " +       // 판매 날짜
            "FROM order_item o " +  
            "WHERE DATE(o.reg_date) = DATE(DATE_ADD(NOW(), INTERVAL -1 DAY)) " + // 하루 전 날짜 데이터만 조회
            "INNER JOIN product p ON o.orderPname = p.productName" + // 주문 항목과 제품 연결
            "GROUP BY p.category_id, DATE(o.reg_date)" // 카테고리와 날짜별로 그룹화
    )
    @Options(useGeneratedKeys = true, keyProperty = "profitId", keyColumn = "profit_id")
    public int insert();

    
    @Update("UPDATE profit SET total_count = #{totalCount} WHERE profit_id = #{profitId}")
    int update(Profit input);

    @Delete("DELETE FROM profit WHERE profit_id = #{profitId}")
    int delete(Profit input);

   
    // 카테고리별 판매량 조회
    @Select("SELECT profit_id, category_id, total_count, reg_date " +
            "FROM profit " +
            "WHERE profit_id = #{profitId}")
    @Results(id = "CategoryProfitMap", value = {
    @Result(property = "profitId", column = "profit_id"),
    @Result(property = "categoryId", column = "category_id"),
    @Result(property = "totalCount", column = "total_count"),
    @Result(property = "regDate", column = "reg_date")
    })
    Profit selectItem(Profit input);



    // 1주일 판매량 조회
    @Select("SELECT " + 
                "profit_id, " + 
                "category_id, " +
                "total_count, " + 
                "reg_date " + 
            "FROM profit " + 
            "ORDER BY reg_date DESC, category_id ASC " + 
            "LIMIT 7")
    @ResultMap("CategoryProfitMap")
    List<Profit> selectWeeklyProfit();


    // 1개월 판매량 조회
//     @Select("SELECT " + 
//                 "category_id, " +
//                 "SUM(total_count) AS total_count, " +
//                 "CONCAT(DATE_FORMAT(MIN(reg_date), '%Y-%m-%d'), ' ~ ', DATE_FORMAT(MAX(reg_date), '%Y-%m-%d')) AS reg_date " +
//         "FROM profit " +
//         "WHERE reg_date >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH) " + // 최근 한 달 데이터
//         "GROUP BY category_id, FLOOR(DATEDIFF(CURDATE(), reg_date) / 7) " + // 주 단위 그룹화
//         "ORDER BY MIN(reg_date) DESC, category_id ASC " + // 최신 날짜 순으로 정렬
//         "LIMIT 4") // 최신 4주 데이터만 조회


@Select("SELECT " +
                "category_id AS category_id, " +         // 카테고리 ID
                "SUM(total_count) AS total_count, " +    // 총 판매량
                "DATE_FORMAT(reg_date, '%Y-%m') AS reg_date " + // 월 단위 날짜 포맷
        "FROM profit " +
        "WHERE DATE_FORMAT(reg_date, '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m') " + // 현재 달 데이터 조회
        "GROUP BY category_id, DATE_FORMAT(reg_date, '%Y-%m') " + // 카테고리와 월별 그룹화
        "ORDER BY category_id ASC, reg_date DESC " + // 정렬: 카테고리 ID와 날짜
        "LIMIT 0, 4")
    @Results(id = "MonthlyProfitMap", value = {
    @Result(property = "categoryId", column = "category_id"),
    @Result(property = "totalCount", column = "total_count"),
    @Result(property = "regDate", column = "reg_date")
    })
    List<Profit> selectMonthlyProfit();


    @Select("SELECT COUNT(*) FROM profit WHERE profit_id = #{profitId}")
    int selectCount(Profit input);

}
