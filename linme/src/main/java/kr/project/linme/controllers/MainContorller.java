package kr.project.linme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainContorller {
  /**
   * 메인페이지
   * 
   * @param model
   * @return
   */
  @GetMapping("/main/main")
  public String main(Model model) {
    return "main/main";
  }

  /**
   * 헤더 카테고리 클릭시 페이지 이동
   */
  @Controller
  public class headerCategoryController {
    @GetMapping("/header/categoryList")
    public String categoryList(Model model) {
      model.addAttribute("category", "category");
      return "header/category_list";
    }

    @GetMapping("/main/main_ok")
    public String mainOk(Model model) {
      return "main/main_ok";
    }

    @GetMapping("/main/list_no1")
    public String listNo1(Model model) {
      return "main/list_no1";
    }

    @GetMapping("/main/list_no2")
    public String listNo2(Model model) {
      return "main/list_no2";
    }

    @GetMapping("/main/list_no3")
    public String listNo3(Model model) {
      return "main/list_no3";
    }

    @GetMapping("/main/list_no4")
    public String listNo4(Model model) {
      return "main/list_no4";
    }

    @GetMapping("/header/new")
    public String New(Model model) {
      return "header/new";
    }

    @GetMapping("/header/best")
    public String Best(Model model) {
      return "header/best";
    }

    @GetMapping("/header/spacial")
    public String Spacial(Model model) {
      return "header/spacial";
    }

    /**
     * 상세페이지
     * 
     * @param model
     * @return
     */
    @GetMapping("/view/view")
    public String View(Model model) {
      return "view/view";
    }
  

  }
}
