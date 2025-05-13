package com.thymeleaf.thymeleaf_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/hello-world")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World");
        return "hello-world";
    }

    @GetMapping("/link-expression")
    public String linkExpression(Model model) {
        model.addAttribute("id", 1);
        return "link-expression";
    }

    @GetMapping("/fragment-expression")
    public String fragmentExpression(Model model) {
        return "fragment-expression";
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy";
    }
}
