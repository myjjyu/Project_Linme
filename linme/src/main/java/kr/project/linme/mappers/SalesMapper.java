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
    @Insert(
            "INSERT INTO sales (sales_total, sales_date) "+      
            "SELECT " + 
                "SUM(p.total_price) AS total_price, " +
                "DATE(p.reg_date) AS dt " + 
            "FROM payment p "+
            "WHERE DATE(p.reg_date) = DATE(DATE_ADD(NOW(), INTERVAL -1 DAY))"+
            "GROUP BY dt"
        )
    @Options(useGeneratedKeys = true, keyProperty = "salesId", keyColumn = "sales_id")
    public int insert();

    @Update("...")
    public int update(Sales input);

    @Delete("...")
    public int delete(Sales input);

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

    @Select("...")
    @ResultMap("resultMap")
    public List<Sales> selectList(Sales input);

    @Select("...")
    public int selectCount(Sales input);
}
