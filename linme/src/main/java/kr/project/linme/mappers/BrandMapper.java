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

import kr.project.linme.models.Brand;

@Mapper
public interface BrandMapper {

    // 브랜드 정보 삽입
    @Insert("INSERT INTO brand (" +
            "brand_name, reg_date, edit_date) " +
            "VALUES (#{brandName}, now(), now())")
    @Options(useGeneratedKeys = true, keyProperty = "brandId", keyColumn = "brand_id")
    public int insert(Brand input);

    // 브랜드 정보 수정
    @Update("UPDATE brand SET " +
            "brand_name = #{brandName}, " +
            "reg_date = #{regDate}, " +
            "edit_date = #{editDate} " +
            "WHERE brand_id = #{brandId}")
    public int update(Brand input);

    // 브랜드 삭제
    @Delete("DELETE FROM brand WHERE brand_id = #{brandId}")
    public int delete(Brand input);

    // 단일 브랜드 조회
    @Select("SELECT brand_name, reg_date, edit_date FROM brand WHERE brand_id = #{brandId}")
    @Results(id = "brandMap", value = {
        @Result(property = "brandId", column = "brand_id"),
        @Result(property = "brandName", column = "brand_name"),
        @Result(property = "regDate", column = "reg_date"),
        @Result(property = "editDate", column = "edit_date")
    })
    public Brand selectItem(Brand input);

    // 브랜드 다중 조회
    @Select("SELECT brand_id, brand_name, reg_date, edit_date FROM brand")
    @ResultMap("brandMap")
    public List<Brand> selectList(Brand input);

    // 브랜드 수 조회
    @Select("SELECT COUNT(*) FROM brand")
    public int selectCount(Brand input);
}