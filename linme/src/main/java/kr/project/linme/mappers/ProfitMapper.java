package kr.project.linme.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.project.linme.models.Profit;

@Mapper
public interface ProfitMapper {
        @Insert(
                "INSERT INTO profit (category_id, total_count, reg_date) " + 
                "SELECT " +
                    "p.category_id, " +                     // 카테고리 ID
                    "SUM(o.order_count) AS total_count, " + // 총 판매량
                    "DATE(o.reg_date) AS reg_date " +       // 판매 날짜
                "FROM order_item o " +  
                "INNER JOIN product p ON o.orderPname = p.productName" + // 주문 항목과 제품 연결
                "GROUP BY p.category_id, DATE(o.reg_date)" // 카테고리와 날짜별로 그룹화
            )
    @Options(useGeneratedKeys = true, keyProperty = "profitId", keyColumn = "profit_id")
    public int insert(Profit input);

    @Update("...")
    public int update(Profit input);

    @Delete("...")
    public int delete(Profit input);

    @Select("...")
    @Results(id="resultMap", value={
        @Result(property="...", column="..."),
        @Result(property="...", column="..."),
        @Result(property="...", column="...")
    })
    public Profit selectItem(Profit input);

       // 카테고리별 판매량 조회
       @Select("SELECT " +
                "reg_date, " +       // 판매 날짜
                "category_id, " +    // 카테고리 ID
                "category_name, " +  // 카테고리 이름
                "total_count " +     // 총 판매량
                "FROM profit " +
                "ORDER BY reg_date ASC, total_count DESC") // 날짜순, 판매량 내림차순 정렬
        public List<Profit> selectList(Profit input);



    @Select("...")
    public int selectCount(Profit input);
}
