package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Category {

  private int categoryId;             // 카테고리 일련번호
  private String categoryName;        // 카테고리명
  private String iconCategory;        // 아이콘 카테고리
  private String titleCategory;       // 타이틀 카테고리
  private String headerItem;          // 카테고리 헤더 아이템
  private String regDate;             // 등록일시
  private String editDate;            // 변경일시

  @Getter
  @Setter
  private static int listCount = 0;

  @Getter
  @Setter
  private static int offset = 0;
}