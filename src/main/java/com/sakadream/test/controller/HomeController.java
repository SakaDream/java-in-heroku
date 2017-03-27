package com.sakadream.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sakadream.test.bean.Employee;
import com.sakadream.test.model.Functions;;

@Controller
public class HomeController {
    Functions fn = new Functions();
    HttpSession session;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
            ModelMap model) throws Exception {
        if (fn.checkLogin(username, password, session)) {
            model.addAttribute("list", fn.showAllEmployees());
            return "redirect:employees.htm";
        } else {
            model.addAttribute("error", 1);
            return "index";
        }
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String employees(ModelMap model) throws Exception {
        if (fn.checkSession(session)) {
            model.addAttribute("list", fn.showAllEmployees());
            fn.cleanConnection();
            return "redirect:employees.htm";
        } else {
            return "redirect:index.htm";
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "add";
    }

    @RequestMapping(value = "/add-post", method = RequestMethod.POST)
    public String addPost(@RequestParam("fullname") String fullName, @RequestParam("address") String address,
            @RequestParam("email") String email, @RequestParam("phone") String phone,
            @RequestParam("salary") String salary, ModelMap model) throws Exception {
        fn.add(new Employee(fullName, address, email, phone, Integer.valueOf(salary)));
        model.addAttribute("list", fn.showAllEmployees());
        return "redirect:employees.htm";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") String id, ModelMap model) throws Exception {
        try {
            model.addAttribute("e", fn.getEmployee(Integer.valueOf(id)));
            return "edit";
        } catch (Exception e) {
            model.addAttribute("list", fn.showAllEmployees());
            return "employees";
        }
    }

    @RequestMapping(value = "/edit-post", method = RequestMethod.POST)
    public String editPost(@RequestParam("fullname") String fullName, @RequestParam("address") String address,
            @RequestParam("email") String email, @RequestParam("phone") String phone,
            @RequestParam("salary") String salary, @RequestParam("id") String id, ModelMap model) throws Exception {
        fn.edit(Integer.valueOf(id), new Employee(fullName, address, email, phone, Integer.valueOf(salary)));
        model.addAttribute("list", fn.showAllEmployees());
        return "redirect:employees.htm";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") String id, ModelMap model) throws Exception {
        fn.delete(Integer.valueOf(id));
        model.addAttribute("list", fn.showAllEmployees());
        return "redirect:employees.htm";
    }
}