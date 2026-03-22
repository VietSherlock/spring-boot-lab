package com.vietlnn.springbootlab.springboot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class DemoMvcController {

  @RequestMapping("/hello")
  public String sayHello(Model model) {
    model.addAttribute("theDate", java.time.LocalDateTime.now());
    return "helloworld";
  }
}
