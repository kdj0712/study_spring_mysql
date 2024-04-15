package com.example.co_templates.controllers;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TemplateJSP {
    @GetMapping("/templatewithJSP")
    public ModelAndView templatewithJSP(){
        String viewPath = "/WEB-INF/views/template.jsp";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(viewPath);
        return modelAndView;
    }

    @GetMapping("/templatewithJSPStatments")
    public ModelAndView templatewithJSPStatments(ModelAndView modelAndView){
        modelAndView.addObject("name", "cocolang");
        modelAndView.addObject("age", "1");

        String viewPath = "/WEB-INF/views/template_jspstatements.jsp";
        modelAndView.setViewName(viewPath);
        return modelAndView;
    }

    @GetMapping("/templatewithServletStatments")
    public String templatewithServletStatments(){
        HashMap<String, Object> model = new HashMap<>();
        model.put("name", "cocolang");
        model.put("age", "1");

        int day = 3;

        String htmlAll = "<html>";
        htmlAll = htmlAll + "<head><title>IF...ELSE Example</title></head>";
        htmlAll = htmlAll + "<body>";
        if (day == 1 || day == 7) {
            htmlAll = htmlAll + "<p> Today is weekend</p>"; 
        } else {
        }
        htmlAll = htmlAll + "</body>";
        htmlAll = htmlAll + "</html>";
        
        htmlAll = htmlAll + "<div>";
        String name = (String)model.get("name");
        htmlAll = htmlAll + "<div>name : "+name+"</div>";
        String age = (String)model.get("age");
        htmlAll = htmlAll + "<div>age : "+age+"</div>";
        htmlAll = htmlAll + "</div>";

        return htmlAll;
    }
}
