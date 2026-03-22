package com.vietlnn.springbootlab.springboot.mvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc")
public class HelloWorldController {

  // show initial HTML form
  @RequestMapping("/showForm")
  public String showForm() {
    return "helloworld-form";
  }

  // process the HTML form
  @RequestMapping("/processForm")
  public String processForm() {
    return "helloworld-process-form";
  }

  // process the HTML form v2
  @RequestMapping("/processFormV2")
  public String processFormV2(HttpServletRequest request, Model model) {

    // read the request parameter from HTML form
    String studentName = request.getParameter("studentName");

    // upper case the name
    studentName = studentName.toUpperCase();

    // create the message
    String result = "Yo! " + studentName;

    // add message to Model
    model.addAttribute("message", result);

    return "helloworld-process-form";
  }
}
