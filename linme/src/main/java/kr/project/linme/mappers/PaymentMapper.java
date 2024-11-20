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

import kr.project.linme.models.Payment; 

@Mapper
public interface PaymentMapper {
    @Insert("INSERT INTO payment "+
            "(order_name, order_tel, addr1, addr2, "+
            "addr_name, addr_msg, discount_price, "+
            "total_price, reg_date, edit_date) " +
            "VALUES (#{orderName}, #{orderTel}, #{addr1}, "+
            "#{addr2}, #{addrName}, #{addrMsg}, #{discountPrice}, "+
            "#{totalPrice}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "paymentId", keyColumn = "payment_id")
    public int insert(Payment input);

    @Update("UPDATE payment SET " +
                "order_name = #{orderName}, order_tel = #{orderTel}, "+
                "addr1 = #{addr1}, addr2 = #{addr2}, addr_name = #{addrName},"+ 
                "addr_msg = #{addrMsg}, discount_price = #{discountPrice}, "+
                "total_price = #{totalPrice}, edit_date = NOW() " +
            "WHERE payment_id = #{paymentId}")
    public int update(Payment input);

    @Delete("DELETE FROM payment WHERE payment_id = #{paymentId}")
    public int delete(Payment input);

    /**
     * 단일행 조회를 수행하는 메서드 정의
     * @param input
     * @return
     */
    @Select("SELECT "+
                "order_name, order_tel, addr1, addr2, "+
                "addr_name, addr_msg, discount_price, " + 
                "total_price, reg_date, edit_date " +
            "FROM payment WHERE payment_id = #{paymentId}")
    @Results(id="PaymentMap", value={
        @Result(property = "paymentId", column = "payment_id"),
        @Result(property = "orderName", column = "order_name"),
        @Result(property = "orderTel", column = "order_tel"),
        @Result(property = "addr1", column = "addr1"),
        @Result(property = "addr2", column = "addr2"),
        @Result(property = "addrName", column = "addr_name"),
        @Result(property = "addrMsg", column = "addr_msg"),
        @Result(property = "discountPrice", column = "discount_price"),
        @Result(property = "totalPrice", column = "total_price"),
        @Result(property = "regDate", column = "reg_date"),
        @Result(property = "editDate", column = "edit_date")
    })
    public Payment selectItem(Payment input);

    @Select("SELECT "+
               "payment_id, order_name, order_tel, addr1, addr2, " + 
                "addr_name, addr_msg, discount_price," + 
                "total_price, reg_date, edit_date " +
            "FROM payment "+
            "WHERE payment_id = #{paymentId}")
    @ResultMap("PaymentMap")
    public List<Payment> selectList(Payment input);

    @Select("SELECT COUNT(*) FROM payment")
    public int selectCount(Payment input);
}