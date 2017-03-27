package com.sakadream.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sakadream.test.model.Database;

@Controller
@RequestMapping(method = RequestMethod.POST)
public class ProcessController {
    Database db = new Database();
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, 
        @RequestParam("password") String password, ModelMap model) throws Exception {
            if(db.checkLogin(username, password)) {
                return "redirect:/employees.htm"; 
            } else {
                model.addAttribute("error", true);
                return "redirect:/";
            }
    }
}