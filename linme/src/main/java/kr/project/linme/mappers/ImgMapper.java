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
     * @param input - 저장할 이미지 정보에 대한 모델 객체
     * @return 입력된 데이터 수
     */
    @Insert("INSERT INTO img (" + 
                "product_id, " + 
                "img_url, " + 
                "img_path, " + 
                "img1, " + 
                "img2, " + 
                "img3, " + 
                "d_img1, " + 
                "d_img2, " + 
                "d_img3, " + 
                "reg_date, " + 
                "edit_date) " + 
            "VALUES (" + 
                "#{productId}, " + 
                "#{imgUrl}, " + 
                "#{imgPath}, " + 
                "#{img1}, " + 
                "#{img2}, " + 
                "#{img3}, " + 
                "#{dImg1}, " + 
                "#{dImg2}, " + 
                "#{dImg3}, " + 
                "NOW(), " + 
                "NOW()" + 
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "imgId", keyColumn = "img_id")
    public int insert(Img input);

    /**
     * 이미지 수정
     * @param input - 이미지 수정에 대한 모델 객체
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
                "edit_date = NOW() " + 
            "WHERE img_id = #{imgId}")
    public int update(Img input);

    /**
     * 이미지 삭제
     * @param input - 이미지 삭제에 대한 모델 객체
     * @return 삭제된 데이터 수
     */
    @Delete("DELETE FROM img WHERE img_id = #{imgId}")
    public int delete(Img input);

    /**
     * 이미지 단일 조회
     * @param input - 이미지 조회에 대한 모델 객체
     * @return 조회한 데이터 수
     */
    @Select("SELECT " + 
            "img_id, " + 
            "product_id, " + 
            "img_url, " + 
            "img_path, " + 
            "img1, " + 
            "img2, " + 
            "img3, " + 
            "d_img1, " + 
            "d_img2, " + 
            "d_img3, " + 
            "reg_date, " + 
            "edit_date " + 
            "FROM img " + 
            "WHERE img_id = #{imgId}")
    @Results(id="imgMap", value={
        @Result(property="imgId", column="img_id"),
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
     * @param input - 이미지 조회에 대한 모델 객체
     * @return 조회한 데이터 수
     */
    @Select("SELECT " + 
                "img_id, " + 
                "product_id, " + 
                "img_url, " + 
                "img_path, " + 
                "img1, " + 
                "img2, " + 
                "img3, " + 
                "d_img1, " + 
                "d_img2, " + 
                "d_img3, " + 
                "reg_date, " + 
                "edit_date " + 
            "FROM img")
    @ResultMap("imgMap")
    public List<Img> selectList(Img input);

    /**
     * 이미지 수 조회
     * @param input 
     * @return 조회한 데이터 수
     */
    @Select("SELECT COUNT(*) FROM img")
    public int selectCount(Img input);
}
