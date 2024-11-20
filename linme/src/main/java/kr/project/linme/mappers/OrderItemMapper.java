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

import kr.project.linme.models.OrderItem; 

@Mapper
public interface OrderItemMapper {
    @Insert("INSERT INTO order_item "+
            "(order_bname, order_pname, "+
            "order_count, order_sprice, order_price, payment_id, "+
            "reg_date, edit_date) " +
            "VALUES (#{orderBname}, #{orderPname}, "+
            "#{orderCount}, #{orderSprice}, #{orderPrice}, #{paymentId}, "+
            "NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "orderItemId", keyColumn = "order_item_id")
    public int insert(OrderItem input);

    @Update("UPDATE order_item SET " +
            "order_bname = #{orderBname}, order_pname = #{orderPname}, "+
            "order_count = #{orderCount}, order_sprice = #{orderSprice}, "+
            "order_price = #{orderPrice}, edit_date = NOW() " +
            "WHERE order_item_id = #{orderItemId}")
    public int update(OrderItem input);

    @Delete("DELETE FROM order_item WHERE order_item_id = #{orderItemId}")
    public int delete(OrderItem input);

    /**
     * 단일행 조회를 수행하는 메서드 정의
     * @param input
     * @return
     */
    @Select("SELECT "+
                "order_item_id, order_bname, order_pname, "+
                "order_count, order_sprice, order_price, payment_id, " + 
                "reg_date, edit_date "+
            "FROM order_item "+
            "WHERE order_item_id = #{orderItemId}")
    @Results(id="OrderItemMap", value={
        @Result(property = "orderItemId", column = "order_item_id"),
        @Result(property = "orderBname", column = "order_bname"),
        @Result(property = "orderPname", column = "order_pname"),
        @Result(property = "orderCount", column = "order_count"),
        @Result(property = "orderSprice", column = "order_sprice"),
        @Result(property = "orderPrice", column = "order_price"),
        @Result(property = "paymentId", column = "payment_id"),
        @Result(property = "regDate", column = "reg_date"),
        @Result(property = "editDate", column = "edit_date")
    })
    public OrderItem selectItem(OrderItem input);

    @Select("SELECT "+
                "order_item_id, order_bname, order_pname, "+
                "order_count, order_sprice, order_price, "+
                "payment_id, reg_date, edit_date "+ 
            "FROM order_item "+
            "WHERE order_item_id = #{orderItemId}")
    @ResultMap("OrderItemMap")
    public List<OrderItem> selectList(OrderItem input);

    @Select("SELECT COUNT(*) FROM order_item")
    public int selectCount(OrderItem input);
}
