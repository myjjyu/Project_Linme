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
}
