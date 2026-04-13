package com.vietlnn.springbootlab.springboot.mvc.controller;

import com.vietlnn.springbootlab.springboot.mvc.model.StudentMvc;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/mvc")
public class HelloWorldController {

  // define logger using slf4j
  //  private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

  // inject config setting and split string into a list
  @Value("${student.countries}")
  private List<String> countries;

  // show HTML form
  @GetMapping("/showForm/v2")
  public String showFormV2(Model model) {

    StudentMvc studentMvc = new StudentMvc();

    // create a bean/instance of Student to use in the HTML
    model.addAttribute("student", studentMvc);
    model.addAttribute("countries", countries);

    // display the form
    return "helloworld-form";
  }

  // process the HTML form
  @PostMapping("/processForm/v2")
  public String processFormV2(@ModelAttribute("student") StudentMvc student) {

    // log the input data
    log.info(
        "Student's name: {} {}. He comes from {}",
        student.getFirstName(),
        student.getLastName(),
        countries);

    return "helloworld-process-form";
  }

  // show HTML form
  /*
  @GetMapping("/showForm/v1")
  public String showFormV1() {
    return "helloworld-form";
  }
  */

  // process the HTML form
  /*
  @PostMapping("/processForm/v1")
  public String processFormV1(@RequestParam("studentName") String name, Model model) {

    // create the message
    String result = "Yo! " + name.toUpperCase();

    // add message to Model
    model.addAttribute("message", result);

    return "helloworld-process-form";
  }
  */
}
