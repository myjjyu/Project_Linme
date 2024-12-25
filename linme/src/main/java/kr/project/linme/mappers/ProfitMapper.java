package kr.project.linme.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.project.linme.models.Profit;

@Mapper
public interface ProfitMapper {

    // 카테고리별 판매량+판매액 조회
    @Select("SELECT " +
            "DATE(reg_date) AS date, " +           // 날짜
            "category_id, " +                    // 카테고리 ID
            "SUM(order_count) AS product_count, " + // 판매 수량
            "SUM(order_count * sale_price) AS product_sale " + // 판매 금액
            "FROM profit " +
            "GROUP BY DATE(reg_date), category_id " + // 날짜와 카테고리로 그룹화
            "ORDER BY date ASC, category_id ASC")  // 날짜 순, 카테고리 순 정렬
    List<Profit> CategorySale();


    // 통합 테이블 생성 (판매수량 + 판매매출액)
    @Select("CREATE TABLE IF NOT EXISTS CategoryProfit (" +
            "profit_id INT AUTO_INCREMENT PRIMARY KEY, " +
            "category_id INT NOT NULL, " +
            "product_count INT NOT NULL, " +
            "product_sale INT NOT NULL, " +
            "reg_date DATETIME NOT NULL DEFAULT NOW())")
    void CategoryProfit();

    // 통합 테이블에 데이터 삽입 (판매수량 + 판매매출액)
    @Insert("INSERT INTO CategoryProfit (category_id, product_count, product_sale, reg_date) " +
                "SELECT " +
                "category_id, " +
                "SUM(order_count) AS product_count, " +
                "SUM(order_count * sale_price) AS product_sale, " +
                "reg_date " +
                "FROM profit " +
                "GROUP BY DATE(reg_date), category_id")
    void insertCategoryProfit();
}
