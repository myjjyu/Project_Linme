package kr.project.linme.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Category {

  private int id;                   // 카테고리 일련번호 
  private String category_name;     // 카테고리명 
  private String icon_category;     // 아이콘 카테고리
  private String title_category;    // 타이틀 카테고리
  private String header_item;       // 카테고리 헤더 아이템
  private String reg_date;          // 등록일시
  private String edit_date;         // 변경일시

  @Getter
  @Setter
  private static int listCount = 0;

  @Getter
  @Setter
  private static int offset = 0;
}