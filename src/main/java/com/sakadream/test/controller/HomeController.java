package com.sakadream.test.controller;

@Controller
public class HomeController {
    @RequestMapping("index")
    public void index() {
        return "index";
    }
}