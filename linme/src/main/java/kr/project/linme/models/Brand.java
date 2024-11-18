package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Brand {
    private int brandId;                // 브랜드 일련번호
    private String brandName;           // 브랜드명
    private String regDate;             // 등록일시
    private String editDate;            // 변경일시

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}
