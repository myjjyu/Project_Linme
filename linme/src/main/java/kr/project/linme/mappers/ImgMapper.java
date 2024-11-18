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

import kr.project.linme.models.Img;

@Mapper
public interface ImgMapper {
    /**
     * 이미지 저장
     * @param input
     * @return
     */
    @Insert("INSERT INTO img (" + 
            "product_id, img_url, img_path, " + 
            "img1, img2, img3, " + 
            "d_img1, d_img2, d_img3, " + 
            "reg_date, edit_date) " + 
            "VALUES (" + 
            "#{productId}, #{imgUrl}, #{imgPath}, " + 
            "#{img1}, #{img2}, #{img3}, " + 
            "#{dImg1}, #{dImg2}, #{dImg3}, " + 
            "#{regDate}, #{editDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int insert(Img input);

    /**
     * 이미지 수정
     * @param input
     * @return
     */
    @Update("UPDATE img SET " + 
            "product_id = #{productId}, " + 
            "img_url = #{imgUrl}, " + 
            "img_path = #{imgPath}, " + 
            "img1 = #{img1}, " + 
            "img2 = #{img2}, " + 
            "img3 = #{img3}, " + 
            "d_img1 = #{dImg1}, " + 
            "d_img2 = #{dImg2}, " + 
            "d_img3 = #{dImg3}, " + 
            "reg_date = #{regDate}, " + 
            "edit_date = #{editDate} " + 
            "WHERE id = #{id}")
    public int update(Img input);

    /**
     * 이미지 삭제
     * @param input
     * @return
     */
    @Delete("DELETE FROM img WHERE id = #{id}")
    public int delete(Img input);

    /**
     * 이미지 단일 조회
     * @param input
     * @return
     */
    @Select("SELECT " + 
            "id, product_id, img_url, img_path, " + 
            "img1, img2, img3, " + 
            "d_img1, d_img2, d_img3, " + 
            "reg_date, edit_date " + 
            "FROM img " + 
            "WHERE id = #{id}")
    @Results(id="imgMap", value={
        @Result(property="id", column="id"),
        @Result(property="productId", column="product_id"),
        @Result(property="imgUrl", column="img_url"),
        @Result(property="imgPath", column="img_path"),
        @Result(property="img1", column="img1"),
        @Result(property="img2", column="img2"),
        @Result(property="img3", column="img3"),
        @Result(property="dImg1", column="d_img1"),
        @Result(property="dImg2", column="d_img2"),
        @Result(property="dImg3", column="d_img3"),
        @Result(property="regDate", column="reg_date"),
        @Result(property="editDate", column="edit_date")
    })
    public Img selectItem(Img input);

    /**
     * 이미지 다중 조회
     * @param input
     * @return
     */
    @Select("SELECT " + 
            "id, product_id, img_url, img_path, " + 
            "img1, img2, img3, " + 
            "d_img1, d_img2, d_img3, " + 
            "reg_date, edit_date " + 
            "FROM img")
    @ResultMap("imgMap")
    public List<Img> selectList(Img input);

    /**
     * 이미지 수 조회
     * @param input
     * @return
     */
    @Select("SELECT COUNT(*) FROM img")
    public int selectCount(Img input);
}
