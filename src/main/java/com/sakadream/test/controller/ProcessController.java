package com.sakadream.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sakadream.test.bean.Employee;
import com.sakadream.test.model.Database;

@Controller
@RequestMapping(method = RequestMethod.POST)
public class ProcessController {
    Database db = new Database();

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
            ModelMap model) throws Exception {
        if (db.checkLogin(username, password)) {
            model.addAttribute("list", db.showAllEmployees());
            return "redirect:employees.htm";
        } else {
            model.addAttribute("error", 1);
            return "index";
        }
    }

    @RequestMapping("/add-post")
    public String addPost(@RequestParam("fullname") String fullName, @RequestParam("address") String address,
            @RequestParam("email") String email, @RequestParam("phone") String phone,
            @RequestParam("salary") String salary, ModelMap model) throws Exception {
        db.add(new Employee(fullName, address, email, phone, Integer.valueOf(salary)));
        model.addAttribute("list", db.showAllEmployees());
        return "redirect:employees.htm";
    }

    @RequestMapping("/edit-post")
    public String editPost(@RequestParam("fullname") String fullName, @RequestParam("address") String address,
            @RequestParam("email") String email, @RequestParam("phone") String phone,
            @RequestParam("salary") String salary, @RequestParam("id") String id, ModelMap model) throws Exception {
        db.edit(Integer.valueOf(id), new Employee(fullName, address, email, phone, Integer.valueOf(salary)));
        model.addAttribute("list", db.showAllEmployees());
        return "redirect:employees.htm";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") String id, ModelMap model) throws Exception {
        db.delete(Integer.valueOf(id));
        model.addAttribute("list", db.showAllEmployees());
        return "redirect:employees.htm";
    }
}