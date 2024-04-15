package com.example.co_templates.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.co_templates.services.CommonCodeService;

@Controller
public class CommonCodeControllers {

    @Autowired
    CommonCodeService commonCodeService;
    @GetMapping("/commonCode/list")
    public ModelAndView list(ModelAndView modelAndView) {
        ArrayList<HashMap<String, Object>> itemList = new ArrayList<HashMap<String, Object>>();
        // Call Service with Pure Java
        // CommonCodeService commonCodeService = new CommonCodeService();
        itemList = commonCodeService.list(1);

        String viewPath = "/WEB-INF/views/commoncode/template.jsp";
        modelAndView.setViewName(viewPath);
        modelAndView.addObject("itemlist", itemList);
        return modelAndView;
    }
}
