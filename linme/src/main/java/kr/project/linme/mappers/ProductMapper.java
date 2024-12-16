package kr.project.linme.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
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
         * discountPrice: 모델에서 자동계산 만들어서 생략가능
         * 
         * @param input
         * @return
         */
        @Insert("INSERT INTO product (" +
                        "product_name, brand_id, price, sale_price, discount_rate, category_id, discount_price, reg_date, edit_date) "
                        +
                        "VALUES (" +
                        "#{productName}, #{brandId}, #{price}, #{salePrice}, #{discountRate}, #{categoryId}, #{discountPrice}, now(), now())")
        @Options(useGeneratedKeys = true, keyProperty = "productId", keyColumn = "product_id")
        public int insert(Product input);

        // =================================================================================================//

        /**
         * 상품 수정
         * discountPrice : 모델에서 자동계산 만들어서 생략가능
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
                        "category_id = #{categoryId}, " +
                        "discount_price = #{discountPrice}, " +
                        "edit_date = now() " +
                        "WHERE product_id = #{productId}")
        public int update(Product input);

        // =================================================================================================//

        /**
         * 상품 삭제
         * 
         * @param input
         * @return
         */
        @Delete("DELETE FROM product WHERE product_id = #{productId}")
        public int delete(Product input);

        // =================================================================================================//

        /**
         * 상품 단일 조회
         * 
         * @param input
         * @return
         */
        @Select("SELECT " +
                        "p.product_id, p.product_name, p.brand_id, b.brand_name, p.price, p.sale_price, " +
                        "p.discount_rate, p.category_id, p.discount_price, p.reg_date AS p_reg_date, " +
                        "p.edit_date AS p_edit_date, i.img, i.d_img, c.product_count, m.member_id " +
                        "FROM product p " +
                        "INNER JOIN img i ON i.product_id = p.product_id " +
                        "INNER JOIN cart c ON c.product_id = p.product_id " +
                        "INNER JOIN member m ON m.member_id = c.member_id " +
                        "WHERE p.product_id = #{productId}")
        @Results(id = "productMapper", value = {
                        @Result(property = "productId", column = "product_id"),
                        @Result(property = "productName", column = "product_name"),
                        @Result(property = "brandId", column = "brand_id"),
                        @Result(property = "brandName", column = "brand_name"),
                        @Result(property = "price", column = "price"),
                        @Result(property = "salePrice", column = "sale_price"),
                        @Result(property = "discountRate", column = "discount_rate"),
                        @Result(property = "categoryId", column = "category_id"),
                        @Result(property = "discountPrice", column = "discount_price"),
                        @Result(property = "regDate", column = "reg_date"),
                        @Result(property = "editDate", column = "edit_date"),
                        @Result(property = "img", column = "img"),
                        @Result(property = "dImg", column = "d_img"),
                        @Result(property = "productCount", column = "product_count"),
                        @Result(property = "memberId", column = "member_id")
        })
        public Product selectItem(Product input);

        // =================================================================================================//

        /**
         * 상품 목록 조회
         * 
         * @param input
         * @return
         */
        @Select("SELECT " +
                        "p.product_id, p.product_name, p.brand_id, p.price, p.sale_price, " +
                        "p.discount_rate, p.category_id, p.discount_price, p.reg_date AS p_reg_date, " +
                        "p.edit_date AS p_edit_date, i.img, i.d_img " +
                        "FROM product p " +
                        "INNER JOIN img i ON i.product_id = p.product_id")
        @ResultMap("productMapper")
        public List<Product> selectList(Product input);

        // =================================================================================================//

        /**
         * 조건에 맞는 데이터의 개수를 반환
         * 
         * @param categoryId
         * @return 데이터 개수
         */
        @Select("SELECT COUNT(*) FROM product WHERE category_id = #{categoryId}")
        public int selectCount(@Param("categoryId") int categoryId);

        // =================================================================================================//

        /**
         * 검색바 상품 검색 기능
         * 
         * @param keyword
         * @return
         */
        @Select("SELECT p.*, i.img FROM product p " +
                        "LEFT JOIN img i ON i.product_id = p.product_id " +
                        "WHERE p.product_name LIKE CONCAT('%', #{keyword}, '%')")
        @Results({
                        @Result(property = "productId", column = "product_id"),
                        @Result(property = "productName", column = "product_name"),
                        @Result(property = "price", column = "price"),
                        @Result(property = "salePrice", column = "sale_price"),
                        @Result(property = "discountRate", column = "discount_rate"),
                        @Result(property = "img", column = "img")
        })
        List<Product> searchProductsByKeyword(@Param("keyword") String keyword);

        // =================================================================================================//

        /**
         * 카테고리별 상품 조회 기능
         * 
         * @param categoryId
         * @return
         */
        @Select("SELECT " +
                        "p.product_id, p.product_name, p.brand_id, p.price, p.sale_price, " +
                        "p.discount_rate, p.category_id, p.discount_price, p.reg_date AS p_reg_date, " +
                        "p.edit_date AS p_edit_date, i.img, i.d_img " +
                        "FROM product p " +
                        "LEFT JOIN img i ON i.product_id = p.product_id " +
                        "WHERE p.category_id = #{categoryId}")
        @ResultMap("productMapper")
        public List<Product> selectByCategory(@Param("categoryId") int categoryId);

        // =================================================================================================//

        /**
         * 상품 상세페이지 조회
         * 
         * @param productId
         * @return
         */
        @Select("SELECT " +
                        "p.product_id, p.product_name, p.brand_id, b.brand_name, p.price, p.sale_price, " +
                        "p.discount_rate, p.category_id, p.discount_price, p.reg_date AS p_reg_date, " +
                        "p.edit_date AS p_edit_date, i.img, i.d_img " +
                        "FROM product p " +
                        "LEFT JOIN img i ON i.product_id = p.product_id " +
                        "LEFT JOIN brand b ON p.brand_id = b.brand_id " +
                        "LEFT JOIN cart c ON c.product_id = p.product_id " +
                        "WHERE p.product_id = #{productId}")
        @ResultMap("productMapper")
        public Product selectById(@Param("productId") int productId);
}