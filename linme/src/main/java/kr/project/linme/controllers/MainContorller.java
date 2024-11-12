package kr.project.linme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainContorller {
  @GetMapping("/main/main")
  public String main(Model model) {
    return "main/main";
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

  @GetMapping("/main/list_no5")
  public String listNo5(Model model) {
    return "main/list_no5";
  }

  @GetMapping("/main/list_no6")
  public String listNo6(Model model) {
    return "main/list_no6";
  }

  @GetMapping("/main/list_no7")
  public String listNo7(Model model) {
    return "main/list_no7";
  }

  @GetMapping("/main/list_no8")
  public String listNo8(Model model) {
    return "main/list_no8";
  }

  @GetMapping("/main/list_no9")
  public String listNo9(Model model) {
    return "main/list_no9";
  }

  @GetMapping("/main/list_no10")
  public String listNo10(Model model) {
    return "main/list_no10";
  }

  @GetMapping("/main/list_no11")
  public String listNo11(Model model) {
    return "main/list_no11";
  }

  @GetMapping("/header/category_list")
  public String categoryList(Model model) {
    return "header/category_list";
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

  @GetMapping("/view/view")
  public String View(Model model) {
    return "view/view";
  }

}
