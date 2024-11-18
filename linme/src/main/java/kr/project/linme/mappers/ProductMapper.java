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

import kr.project.linme.models.Product;

@Mapper
public interface ProductMapper {

    /**
     * 상품 저장
     * 
     * @param input
     * @return
     */
    @Insert("INSERT INTO product (" +
            "product_name, brand_id, price, sale_price, discount_rate, product_img, detail_img, category_id, header_item, discount_price, reg_date, edit_date) "
            +
            "VALUES (" +
            "#{productName}, #{brandId}, #{price}, #{salePrice}, #{discountRate}, #{productImg}, #{detailImg}, #{categoryId}, #{headerItem}, #{discountPrice}, #{regDate}, #{editDate})")
    @Options(useGeneratedKeys = true, keyProperty = "productId", keyColumn = "product_id")
    public int insert(Product input);

    /**
     * 상품 수정
     * 
     * @param input
     * @return
     */
    @Update("UPDATE product SET " +
            "product_name = #{productName}, " +
            "brand_id = #{brandId}, " +
            "price = #{price}, " +
            "sale_price = #{salePrice}, " +
            "discount_rate = #{discountRate}, " +
            "product_img = #{productImg}, " +
            "detail_img = #{detailImg}, " +
            "category_id = #{categoryId}, " +
            "header_item = #{headerItem}, " +
            "discount_price = #{discountPrice}, " +
            "reg_date = #{regDate}, " +
            "edit_date = #{editDate} " +
            "WHERE product_id = #{productId}")
    public int update(Product input);

    /**
     * 상품 삭제
     * 
     * @param input
     * @return
     */
    @Delete("DELETE FROM product WHERE product_id = #{productId}")
    public int delete(Product input);

    /**
     * 상품 단일 조회
     * 
     * @param input
     * @return
     */
    @Select("SELECT " +
            "product_id, product_name, brand_id, price, sale_price, discount_rate, product_img, " +
            "detail_img, category_id, header_item, discount_price, reg_date, edit_date " +
            "FROM product " +
            "WHERE product_id = #{productId}")
    @Results(id = "productMapper", value = {
            @Result(property = "productId", column = "product_id"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "brandId", column = "brand_id"),
            @Result(property = "price", column = "price"),
            @Result(property = "salePrice", column = "sale_price"),
            @Result(property = "discountRate", column = "discount_rate"),
            @Result(property = "productImg", column = "product_img"),
            @Result(property = "detailImg", column = "detail_img"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "headerItem", column = "header_item"),
            @Result(property = "discountPrice", column = "discount_price"),
            @Result(property = "regDate", column = "reg_date"),
            @Result(property = "editDate", column = "edit_date")
    })
    public Product selectItem(Product input);

    /**
     * 상품 다중 조회
     * 상품 목록을 가져오는 작업
     * WHERE : 조건에 맞는 여러 상품을 가져오는 작업
     * 
     * @param input
     * @return
     */
    @Select("SELECT " +
            "product_id, product_name, brand_id, price, sale_price, discount_rate, product_img, " +
            "detail_img, category_id, header_item, discount_price, reg_date, edit_date " +
            "FROM product " +
            "WHERE category_id = #{categoryId} AND header_item = #{headerItem}")
    @ResultMap("productMapper")
    public List<Product> selectList(Product input);

    /**
     * 상품 개수 조회
     * WHERE : 조건에 맞는 상품이 몇 개 있는지 계산
     * 
     * @param input
     * @return
     */
    @Select("SELECT COUNT(*) FROM product " +
            "WHERE category_id = #{categoryId} " + // 카테고리 조건
            "AND header_item = #{headerItem}")     // 헤더항목 조건
    public int selectCount(Product input);
}
