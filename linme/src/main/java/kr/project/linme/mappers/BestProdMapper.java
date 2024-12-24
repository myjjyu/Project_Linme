package kr.project.linme.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.project.linme.models.BestProd;

@Mapper
public interface BestProdMapper {

        // 인기 상품 데이터 10개 집계해서 Best_prod 테이블에 삽입
        @Insert("INSERT INTO best_prod (order_count, order_item_id, product_name, reg_date) " +
                        "SELECT " +
                        "SUM(oi.order_count) AS order_count, " + // 주문된 상품 수량 집계
                        "p.product_id AS order_item_id, " + // 상품 번호
                        "p.product_name AS product_name, " + // 상품 이름
                        "NOW() AS reg_date " + // 현재 날짜와 시간
                        "FROM order_item oi " + // 주문 테이블
                        "INNER JOIN product p ON oi.order_item_id = p.product_id " + // 상품 테이블과 조인
                        "WHERE oi.reg_date >= NOW() - INTERVAL 1 MONTH " + // 최근 1개월간 주문된 상품만 집계
                        "GROUP BY p.product_id, p.product_name " + // 상품별로 그룹화
                        "ORDER BY order_count DESC " +
                        "LIMIT 10")
        public int insertBestProds();

       
        @Update("...")
        public int update(BestProd input);

        @Delete("...")
        public int delete(BestProd input);

        // 인기 상품 데이터 단일 조회
        @Select("SELECT " +
                        "best_prod_id, " +
                        "order_count, " +
                        "order_item_id, " +
                        "product_name, " +
                        "reg_date " +
                        "FROM best_prod WHERE best_prod_id = #{bestProdId}")
        @Results(id = "BestProdMap", value = {
                        @Result(property = "bestProdId", column = "best_prod_id"),
                        @Result(property = "orderCount", column = "order_count"),
                        @Result(property = "orderItemId", column = "order_item_id"),
                        @Result(property = "productName", column = "product_name"),
                        @Result(property = "regDate", column = "reg_date")
        })
        public BestProd selectItem(BestProd input);

        // 인기 상품 데이터 10개를 조회
        @Select("SELECT best_prod_id, order_count, order_item_id, product_name, reg_date " +
                        "FROM best_prod " +
                        "WHERE reg_date <= CURDATE() - INTERVAL 1 DAY " + // 현재 날짜에서 1일을 빼는 방식으로 수정
                        "ORDER BY reg_date DESC " +
                        "LIMIT 10")
        @ResultMap("BestProdMap")
        public List<BestProd> selectList(BestProd input);

        @Select("...")
        public int selectCount(BestProd input);
}