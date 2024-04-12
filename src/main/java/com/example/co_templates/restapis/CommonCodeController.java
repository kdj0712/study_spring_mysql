package com.example.co_templates.restapis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.co_templates.services.CommonCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class CommonCodeController {

    @Autowired
    CommonCodeService commonCodeService;

    @GetMapping("/r/commonCode/callDao")
    public void callDao(HashMap dataMap){
        commonCodeService.callDao(dataMap);
        return ;
    }

    @GetMapping("/r/commonCode/mixed/{pageNumber}/{pk_id}")
    public ResponseEntity<HashMap<String, Object>> mixed(@PathVariable("pageNumber") Integer pageNumber,
            @PathVariable("pk_id") Integer pkId) {
        // call service
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap = commonCodeService.mixed(pageNumber, pkId);

        // add request params 
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("pageNumber", pageNumber);
        requestParams.put("pk_id", pkId);
        resultMap.put("requestParams", requestParams);

        return ResponseEntity.ok().body(resultMap);
    }

    @GetMapping("/r/commonCode/list/{pageNumber}")
    public ResponseEntity<List<HashMap<String, Object>>> list(@PathVariable("pageNumber") Integer pageNumber) {
        ArrayList<HashMap<String, Object>> itemList = new ArrayList<HashMap<String, Object>>();
        // Call Service with Pure Java
        // CommonCodeService commonCodeService = new CommonCodeService();
        itemList = commonCodeService.list(pageNumber);

        return ResponseEntity.ok().body(itemList);
    }

    @GetMapping("/r/commonCode/view/{pk_id}")
    public ResponseEntity<HashMap<String, Object>> view(@PathVariable("pk_id") Integer pkId) {
        HashMap<String, Object> itemDetails = new HashMap<>();
        itemDetails = commonCodeService.view(pkId);

        return ResponseEntity.ok().body(itemDetails);
    }

}