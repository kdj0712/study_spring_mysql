package com.example.co_templates.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.co_templates.services.CommonCodeService;

@Controller
public class CommonCodeControllers {

    @Autowired
    CommonCodeService commonCodeService;
    @GetMapping("/CommonCode/list")
    public ModelAndView list(ModelAndView modelAndView
                            ,@RequestParam HashMap<String,Object> dataMap
                            ,@RequestParam(name="deleteIds",required = false) ArrayList<String>deleteIds) {
        ArrayList<HashMap<String, Object>> itemList = new ArrayList<HashMap<String, Object>>();
        // Call Service with Pure Java
        // CommonCodeService commonCodeService = new CommonCodeService();
        itemList = (ArrayList<HashMap<String, Object>>) commonCodeService.selectMany(dataMap);

        String viewPath = "/WEB-INF/views/commoncode/list.jsp";
        modelAndView.setViewName(viewPath);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("dataMap", dataMap);
        return modelAndView;
    }

    // @GetMapping("/CommonCode/list_pagination")
    // public ModelAndView listPagenation(ModelAndView modelAndView
    //                         ,@RequestParam HashMap<String,Object> dataMap
    //                         ,@RequestParam(name="deleteIds",required = false) ArrayList<String>deleteIds) {
    //     Object result =commonCodeService.selectSearchWithPagination(dataMap);
    //     String viewPath = "/WEB-INF/views/commoncode/list_pagination.jsp";
    //     modelAndView.setViewName(viewPath);
    //     modelAndView.addObject("result", result);
    //     modelAndView.addObject("dataMap", dataMap);
    //     return modelAndView;
    // }

    @GetMapping("/CommonCode/list_pagination")
    public ModelAndView listPagenation(ModelAndView modelAndView
                            ,@RequestParam HashMap<String,Object> dataMap
                            ,@RequestParam(name="deleteIds",required = false) ArrayList<String>deleteIds) {
        dataMap.put("deleteIds", deleteIds);
        Object result =commonCodeService.selectSearchWithPaginationAndDeletes(dataMap);
        
        String viewPath = "/WEB-INF/views/commoncode/list_pagination.jsp";
        modelAndView.setViewName(viewPath);
        modelAndView.addObject("result", result);
        modelAndView.addObject("dataMap", dataMap);
        return modelAndView;
    }
}
