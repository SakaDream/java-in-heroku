package com.sakadream.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sakadream.test.model.Database;

@Controller
@RequestMapping(method = RequestMethod.GET)
public class RenderController {
    Database db = new Database();
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/employees")
    public String employees(ModelMap model) throws Exception {
        model.addAttribute("list", db.showAllEmployees());
        db.cleanConnection();
        return "employees";
    }

    @RequestMapping("/add")
    public String add() {
        return "add";
    }

    @RequestMapping("/edit")
    public String edit() {
        return "edit";
    }
}