package com.kagankuscu.bottle_tracker.controllers;

import com.kagankuscu.bottle_tracker.models.Bottle;
import com.kagankuscu.bottle_tracker.services.BottleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class BottleController {

    private final BottleService service;

    public BottleController(BottleService service) {
        this.service = service;
    }

    @GetMapping(path = "/")
    public String home() {
        List<Bottle> b = service.findAll();
        return "home";
    }

    @PostMapping(path = "/add")
    public ModelAndView add(@RequestParam("count") Integer count) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/");
        return new ModelAndView(redirectView);
    }
}
