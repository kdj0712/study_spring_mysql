package com.example.co_templates.quests.restapis;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.co_templates.quests.services.QuestVisitorsService;

@RestController
public class QuestVisitorsController {
    @Autowired
    QuestVisitorsService questVisitorsService;


    // localhost:8080/q/r/visitor/callDao/insert
    // {
    //     "WRITER_ID" : "number1"
    //     ,"PK_BOARDS" : "83df4c5d-0beb-45b9-a367-f475d277496e"
    // }

    @GetMapping("/q/r/visitor/callDao/insert")
    public ResponseEntity<Object> callDaoVisitorinsert(@RequestBody HashMap<String,Object> dataMap){
        Object insert = questVisitorsService.insert(dataMap);
        return ResponseEntity.ok().body(insert);
    }

    // localhost:8080/q/r/visitor/callDao/delete/visitor-101
    @GetMapping("/q/r/visitor/callDao/delete/{PK_VISITORS}")
    public ResponseEntity<Object> callDaoVisitordelete(@PathVariable String PK_VISITORS,HashMap<String,Object> dataMap){
        Object delete = questVisitorsService.delete(dataMap,PK_VISITORS);
        return ResponseEntity.ok().body(delete);
    }
}