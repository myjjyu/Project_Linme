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

import kr.project.linme.models.Cart;

@Mapper
public interface CartMapper {
    /**
     * 장바구니 저장
     * @param input 입력할 장바구니 정보에 대한 모델 객체
     * @return 입력된 데이터 수
     */
    @Insert("INSERT INTO cart (" + 
                "product_count, " + 
                "total_price, " + 
                "member_id, " + 
                "product_id, " + 
                "reg_date, " + 
                "edit_date) " + 
            "VALUES (" + 
                "#{productCount}, " + 
                "#{totalPrice}, " + 
                "#{memberId}, " + 
                "#{productId}, " + 
                "NOW(), " + 
                "NOW()" + 
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "cartId", keyColumn = "cart_id")
    public int insert(Cart input);

    /**
     * 장바구니 수정
     * @param input - 수정할 장바구니 정보에 대한 모델 객체
     * @return 수정된 데이터 수
     */
    @Update("UPDATE cart SET " + 
                "product_count = #{productCount}, " + 
                "total_price = #{totalPrice}, " + 
                "member_id = #{memberId}, " + 
                "product_id = #{productId}, " + 
                "edit_date = NOW() " + 
            "WHERE cart_id = #{cartId}")
    public int update(Cart input);

    /**
     * 장바구니 삭제
     * @param input - 삭제할 장바구니 정보에 대한 모델 객체
     * @return 삭제된 데이터 수
     */
    @Delete("DELETE FROM cart WHERE cart_id = #{cartId}")
    public int delete(Cart input);

    /**
     * 장바구니 단일 조회
     * @param input - 조회할 장바구니 정보에 대한 모델 객체
     * @return 조회된 데이터
     */
    @Select("SELECT " + 
                "cart_id, " + 
                "product_count, " + 
                "total_price, " + 
                "member_id, " + 
                "p.product_id, " + 
                "c.reg_date, " + 
                "c.edit_date, " + 
                "product_name, " +
                "price, " +
                "sale_price " +
                "brand_name " +
                "i.img" +
            "FROM cart c " + 
            "INNER JOIN product p ON p.product_id = c.product_id " +
            "INNER JOIN brand b ON b.brand_id = p.brand_id " +
            "INNER JOIN img i ON i.product_id = c.product_id " +
            "WHERE member_id = #{memberId}")
    @Results(id="cartMap", value={
        @Result(property="cartId", column="cart_id"),
        @Result(property="productCount", column="product_count"),
        @Result(property="totalPrice", column="total_price"),
        @Result(property="memberId", column="member_id"),
        @Result(property="productId", column="product_id"),
        @Result(property="regDate", column="reg_date"),
        @Result(property="editDate", column="edit_date"),
        @Result(property="productName", column="product_name"),
        @Result(property="price", column="price"),
        @Result(property="salePrice", column="sale_price"),
        @Result(property="brandName", column="brand_name"),
        @Result(property="img", column="img")
    })
    public Cart selectItem(Cart input);

    /**
     * 장바구니 다중 조회
     * @param input - 조회할 장바구니 정보에 대한 모델 객체
     * @return 조회된 데이터 목록
     */
    @Select("SELECT " + 
                "cart_id, " + 
                "product_count, " + 
                // "SUM(sale_price * product_count) AS total_price, " + 
                "c.total_price, " +
                "member_id, " + 
                "p.product_id, " + 
                "c.reg_date, " + 
                "c.edit_date, " + 
                "p.product_name, " +
                "p.price, " +
                "p.sale_price, " +
                "b.brand_name, " +
                "i.img " +
            "FROM cart c " + 
            "INNER JOIN product p ON p.product_id = c.product_id " +
            "INNER JOIN brand b ON b.brand_id = p.brand_id " +
            "INNER JOIN img i ON i.product_id = c.product_id " +
            "WHERE member_id = #{memberId} " +
            "GROUP BY " + 
                "cart_id, " + 
                "product_count, " + 
                "member_id, " + 
                "p.product_id, " + 
                "c.reg_date, " + 
                "c.edit_date, " + 
                "p.product_name, " + 
                "p.price, " + 
                "p.sale_price, " + 
                "b.brand_name, " + 
                "i.img")
    @ResultMap("cartMap")
    public List<Cart> selectList(Cart input);

    /**
     * 장바구니 수 조회
     * @param input 
     * @return 조회된 데이터 수
     */
    @Select("SELECT COUNT(*) FROM cart")
    public int selectCount(Cart input);

    /**
     * 장바구니 상품 수량 조회
     * @param input 
     * @return 조회된 데이터 수
     */
    @Select("SELECT SUM(product_count) FROM cart WHERE member_id = #{memberId}")
    public int selectProductCount(Cart input);

    /**
     * 장바구니 상품 금액 조회
     * @param input 
     * @return 조회된 데이터 수
     */
    @Select("SELECT SUM(total_price) FROM cart WHERE member_id = #{memberId}")
    public int getTotalPrice(Cart input);
}
