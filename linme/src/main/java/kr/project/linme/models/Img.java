package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Img {
    private int imgId;          // 이미지 일련번호
    private Integer productId;  // 상품 일련번호 (참조키)
    private String imgUrl;      // 이미지 URL
    private String imgPath;     // 이미지 경로
    private String img1;        // 이미지1
    private String img2;        // 이미지2
    private String img3;        // 이미지3
    private String dImg1;       // 상세 이미지1
    private String dImg2;       // 상세 이미지2
    private String dImg3;       // 상세 이미지3
    private String regDate;     // 등록 일시
    private String editDate;    // 변경 일시

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}