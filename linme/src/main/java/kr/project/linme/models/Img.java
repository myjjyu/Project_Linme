package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Img {
    private int imgId;          // 이미지 일련번호
    private Integer productId;  // 상품 일련번호 (참조키)
    private String img;        // 이미지
    private String dImg;       // 상세 이미지
    private String regDate;     // 등록 일시
    private String editDate;    // 변경 일시

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}