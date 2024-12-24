package kr.project.linme.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import kr.project.linme.models.BestProd;

@Mapper
public interface BestProdMapper {

        // 최근 1개월간 주문된 상품 중 인기 상품 10개 집계
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

        // 일별 집계
        @Select("SELECT " +
                        "SUM(oi.order_count) AS order_count, " + // 주문된 상품 수량 집계
                        "p.product_id AS order_item_id, " + // 상품 번호
                        "p.product_name AS product_name, " + // 상품 이름
                        "CURRENT_TIMESTAMP AS reg_date, " + // 현재 날짜와 시간
                        "oi.reg_date AS order_date " + // 실제 주문 날짜
                        "FROM order_item oi " + // 주문 테이블
                        "INNER JOIN product p ON oi.order_item_id = p.product_id " + // 상품 테이블과 조인
                        "WHERE oi.reg_date >= CURRENT_DATE() AND oi.reg_date < CURRENT_DATE() + INTERVAL 1 DAY " + // 주문만집계
                        "GROUP BY p.product_id, p.product_name, oi.reg_date " + // 상품별로 그룹화 및 주문 날짜 포함
                        "ORDER BY order_count DESC " +
                        "LIMIT 10")
        @Results({
                        @Result(property = "orderCount", column = "order_count"),
                        @Result(property = "orderItemId", column = "order_item_id"),
                        @Result(property = "productName", column = "product_name"),
                        @Result(property = "regDate", column = "reg_date"),
                        @Result(property = "orderDate", column = "order_date")
        })
        List<BestProd> dailyBestProds();

        // 주별 집계
        @Select("SELECT SUM(oi.order_count) AS order_count, " +
                        "p.product_id AS order_item_id, " +
                        "p.product_name AS product_name, " +
                        "NOW() AS reg_date, " + // 현재 날짜와 시간
                        "oi.reg_date AS order_date " + // 주문 날짜
                        "FROM order_item oi " +
                        "INNER JOIN product p ON oi.order_item_id = p.product_id " +
                        "WHERE oi.reg_date >= NOW() - INTERVAL 7 DAY " +
                        "GROUP BY p.product_id, p.product_name, oi.reg_date " + // 주문 날짜 포함
                        "ORDER BY order_count DESC " +
                        "LIMIT 5")
        @Results(id = "BestProdMap", value = {
                        @Result(property = "orderCount", column = "order_count"),
                        @Result(property = "orderItemId", column = "order_item_id"),
                        @Result(property = "productName", column = "product_name"),
                        @Result(property = "regDate", column = "reg_date"),
                        @Result(property = "orderDate", column = "order_date")
        })
        List<BestProd> weeklyBestProds();

}
