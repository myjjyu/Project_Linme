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

import kr.project.linme.models.Sales;

@Mapper
public interface SalesMapper {
    /**
     * 매출 집계 데이터 추가
     * @return
     */
    @Insert("INSERT INTO sales (sales_total, sales_date) " +
            "SELECT " +
                "COALESCE(SUM(oi.order_count * oi.order_sprice), 0) AS total_price, " +
                "DATE_FORMAT(oi.reg_date, '%Y-%m-%d') AS dt " +
            "FROM order_item oi " +
            "WHERE DATE(oi.reg_date) = DATE(DATE_ADD(NOW(), INTERVAL -1 DAY)) " +
            "GROUP BY dt")
    @Options(useGeneratedKeys = true, keyProperty = "salesId", keyColumn = "sales_id")
    public int insert(Sales input);

    @Update("...")
    public int update(Sales input);

    /**
     * 31일이 지난 매출 집계 데이터 삭제
     * @param input
     * @return
     */
    @Delete("DELETE FROM sales " +
            "WHERE DATE(sales_date) < DATE(DATE_ADD(NOW(), INTERVAL -31 DAY))")
    public int delete();


    @Select("SELECT " + 
                "sales_id, " + 
                "sales_total, " + 
                "sales_date " + 
            "FROM sales " + 
            "WHERE sales_id=#{salesId}")
    @Results(id="resultMap", value={
        @Result(property="salesId", column="sales_id"),
        @Result(property="salesTotal", column="sales_total"),
        @Result(property="salesDate", column="sales_date")
    })
    public Sales selectItem(Sales input);
    
    /**
     * 주간 매출 집계 데이터 조회
     * @param input
     * @return
     */
    @Select("SELECT " + 
            "sales_id, " + 
            "sales_total, " + 
            "sales_date " + 
        "FROM sales " + 
        "ORDER BY sales_date DESC " + 
        "LIMIT 7")
        @ResultMap("resultMap")
        public List<Sales> selectListW();

    /**
     * 월간 매출 집계 데이터 조회 (주 단위로 최근 4주)
     * @param input
     * @return
     */
    @Select("SELECT " + 
                "CONCAT(DATE_FORMAT(MIN(sales_date), '%Y-%m-%d'), ' ~ ', DATE_FORMAT(MAX(sales_date), '%Y-%m-%d')) AS sales_date, " +
                "SUM(sales_total) AS sales_total " +
            "FROM sales " +
            "GROUP BY FLOOR(DATEDIFF(DATE_ADD(NOW(), INTERVAL -1 DAY), sales_date) / 7) " + // 주 단위로 그룹화화
            "ORDER BY MIN(sales_date) DESC " +
            "LIMIT 0, 4")   // 최근 4주 데이터만 조회
    @ResultMap("resultMap")
    public List<Sales> selectListM();



    @Select("...")
    public int selectCount(Sales input);
}
