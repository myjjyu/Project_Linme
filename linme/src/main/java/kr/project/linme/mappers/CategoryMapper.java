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

import kr.project.linme.models.Category;

@Mapper
public interface CategoryMapper {

        /**
         * 카테고리 저장
         * 
         * @param input
         * @return
         */
        @Insert("INSERT INTO category (" +
                        "category_name, icon_category, title_category, header_item, reg_date, edit_date) " +
                        "VALUES (#{categoryName}, #{iconCategory}, #{titleCategory}, #{headerItem}, now(), now())")
        @Options(useGeneratedKeys = true, keyProperty = "categoryId", keyColumn = "category_id")
        public int insert(Category input);

        /**
         * 카테고리 수정
         * 수정하기 부분에서는 등록일시가 필요하지 않기때문에 제거
         * 
         * @param input
         * @return
         */
        @Update("UPDATE category SET " +
                        "category_name = #{categoryName}, " +
                        "icon_category = #{iconCategory}, " +
                        "title_category = #{titleCategory}, " +
                        "header_item = #{headerItem}, " +
                        "edit_date = now() " +
                        "WHERE category_id = #{categoryId}")
        public int update(Category input);

        /**
         * 카테고리 삭제
         * 
         * @param input
         * @return
         */
        @Delete("DELETE FROM category WHERE category_id = #{categoryId}")
        public int delete(Category input);

        /**
         * 카테고리 단일 조회
         */
        @Select("SELECT " +
                        "category_id, category_name, icon_category, title_category, header_item, reg_date, edit_date " +
                        "FROM category " +
                        "WHERE category_id = #{categoryId}")
        @Results(id = "categoryMap", value = {
                        @Result(property = "categoryId", column = "category_id"),
                        @Result(property = "categoryName", column = "category_name"),
                        @Result(property = "iconCategory", column = "icon_category"),
                        @Result(property = "titleCategory", column = "title_category"),
                        @Result(property = "headerItem", column = "header_item"),
                        @Result(property = "regDate", column = "reg_date"),
                        @Result(property = "editDate", column = "edit_date")
        })
        public Category selectItem(Category input);

        /**
         * 카테고리 다중 조회
         * 
         * @param input
         * @return
         */
        @Select("SELECT " +
                        "category_id, category_name, icon_category, title_category, header_item, reg_date, edit_date " +
                        "FROM category")
        @ResultMap("categoryMap")
        public List<Category> selectList(Category input);

/**
 * 조건에 맞는 카테고리 목록 조회
 * WHERE category_name : 특정 조건 카테고리 항목 조회
 * 
 * @param input
 * @return
 */
@Select("SELECT " +
            "category_id, category_name, icon_category, title_category, header_item, reg_date, edit_date " +
            "FROM category " +
            "WHERE category_name = #{categoryName}")
public List<Category> selectWhereList(Category input);

}
