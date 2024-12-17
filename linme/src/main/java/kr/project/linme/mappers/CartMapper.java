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
     * 장바구니 등록
     * @param input 입력할 장바구니 정보에 대한 모델 객체
     * @return 입력된 데이터 수
     */
    @Insert(
            "INSERT INTO cart (" + 
                "product_count, " + 
                "member_id, " + 
                "product_id, " + 
                "total_price, " + 
                "reg_date, " + 
                "edit_date" + 
            ") VALUES (" + 
                "#{productCount}, " + 
                "#{memberId}, " + 
                "#{productId}, " + 
                "(SELECT sale_price * #{productCount} FROM product WHERE product_id = #{productId}), " + // 계산된 값 삽입
                "NOW(), " + 
                "NOW()" + 
            ")"
            )
    @Options(useGeneratedKeys = true, keyProperty = "cartId", keyColumn = "cart_id")
    public int insert(Cart input);

    /**
     * 장바구니 상품의 수량을 변경
     * @param input - 수정할 장바구니 정보에 대한 모델 객체
     * @return 수정된 데이터 수
     */
    @Update("UPDATE cart SET " + 
                "product_count = #{productCount} " +
                "total_price = (SELECT sale_price * #{productCount} FROM product WHERE product_id = #{productId}), " +
                "edit_date = NOW() " +  
            "WHERE cart_id = #{cartId}")
    public int update(Cart input);

    /**
     * 장바구니 수량 중복 검사
     * @param input
     * @return
     */
    @Update(
            "UPDATE " + 
                "cart c " + 
            "SET " +
                "product_count = product_count + #{productCount} " +
            "WHERE " + 
                "member_id = #{memberId} AND " + 
                "product_id = #{productId}"
            )
    public int updateByUnique(Cart input);

     /**
     * 장바구니 단일 상품 삭제
     * @param input - 삭제할 장바구니 정보에 대한 모델 객체
     * @return 삭제된 데이터 수
     */
    @Delete(
            "<script> " +
                "DELETE FROM cart " + 
                "<where> " +
                    "member_id = #{memberId} " + 
                    "<if test= 'cartId != 0'>AND cart_id = #{cartId}</if> " +
                "</where>" +
            "</script>")
    public int delete(Cart input);

    /**
     * 장바구니 단일 조회
     * @param input - 조회할 장바구니 정보에 대한 모델 객체
     * @return 조회된 데이터
     */
    @Select("SELECT " + 
                "c.cart_id, " + 
                "c.member_id, " + 
                "c.product_count, " + 
                "p.sale_price * c.product_count AS total_price, " + 
                "p.product_id, " + 
                "p.product_name, " + 
                "p.price, " + 
                "p.sale_price, " + 
                "b.brand_name, " + 
                "i.img, " + 
                "c.reg_date, " + 
                "c.edit_date " + 
            "FROM cart c " + 
            "INNER JOIN product p ON p.product_id = c.product_id " +
            "INNER JOIN brand b ON b.brand_id = p.brand_id " +
            "INNER JOIN img i ON i.product_id = c.product_id " +
            "INNER JOIN member m ON m.member_id = c.member_id " +
            "WHERE c.member_id = #{memberId} " +
            "AND c.cart_id = #{cartId}"
            )
    @ResultMap("cartMap")
    public Cart selectItem(Cart input);

    /**
     * 장바구니 다중 조회
     * @param input - 조회할 장바구니 정보에 대한 모델 객체
     * @return 조회된 데이터 목록
     */
    @Select("SELECT " + 
                "c.cart_id, " + 
                "c.product_count, " + 
                "p.sale_price * c.product_count AS total_price, " + 
                "SUM(p.sale_price * c.product_count) AS sum_total_price, " +
                "c.member_id, " + 
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
            "WHERE c.member_id = #{memberId} " +
            "GROUP BY " + 
                "c.cart_id, " + 
                "c.product_count, " + 
                "c.member_id, " + 
                "p.product_id, " + 
                "c.reg_date, " + 
                "c.edit_date, " + 
                "p.product_name, " + 
                "p.price, " + 
                "p.sale_price, " + 
                "b.brand_name, " + 
                "i.img " + 
            "ORDER BY cart_id"
            )
    @Results(id="cartMap", value={
        @Result(property="cartId", column="cart_id"),
        @Result(property="memberId", column="member_id"),
        @Result(property="productId", column="product_id"),
        @Result(property="productCount", column="product_count"),
        @Result(property="totalPrice", column="total_price"),
        @Result(property="sumTotalPrice", column="sum_total_price"),
        @Result(property="totalPrice", column="total_price"),
        @Result(property="regDate", column="reg_date"),
        @Result(property="editDate", column="edit_date"),
        @Result(property="productName", column="product_name"),
        @Result(property="price", column="price"),
        @Result(property="salePrice", column="sale_price"),
        @Result(property="brandName", column="brand_name"),
        @Result(property="img", column="img")
    })
    public List<Cart> selectList(Cart input);

    
    /**
     * 장바구니에 중복된 상품이 있는지 조회
     * @param input 
     * @return 조회된 데이터 수
     */
    @Select("SELECT COUNT(*) FROM cart " + 
            "WHERE member_id = #{memberId} AND product_id = #{productId}"
            )
    public int selectCount(Cart input);

    /**
     * 장바구니 중복 확인
     * @param input
     * @return
     */
    @Select(
            "SELECT " +
                "count(*) AS cnt " +
            "FROM " + 
                "cart " +
            "WHERE " + 
                "member_id = #{memberId} AND " + 
                "product_id = #{productId}"
            )
    public int cartCheck(Cart input);

    @Select(
            "SELECT " +
                "c.cart_id, " + 
                "p.product_id, " + 
                "member_id, " + 
                "product_count, " +
                "p.product_name, " + 
                "p.price, " + 
                "p.sale_price " +                 
            "FROM " + 
                "cart c " +
            "INNER JOIN product p ON c.product_id = p.product_id " +
            "WHERE " + 
                "member_id = #{memberId} AND " + 
                "c.product_id = #{productId}"
            )
    @ResultMap("cartMap")
    public Cart selectUniqueCart(Cart input);

    /**
     * 7일 이상 담겨있는 장바구니 삭제
     * @return
     */
    @Delete("DELETE FROM cart " +
            "WHERE add_date < DATE_ADD(NOW(), INTERVAL -7 day)")
    public int deleteByOverDays();
    
    /**
     * 장바구니에 담긴 상품의 총 금액을 조회
     * @param input
     * @return
     */
    @Select(
            "SELECT " + 
                "COALESCE(SUM(p.sale_price * c.product_count), 0) AS sum_total_price " +
            "FROM " + 
                "cart c " +
            "INNER JOIN product p ON p.product_id = c.product_id " +
            "WHERE " + 
                "c.member_id = #{memberId}"
            )
    public int sumTotalPrice(Cart input);

}
