package com.example.co_templates.quests.restapis;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.co_templates.quests.services.QuestBoardService;

@RestController
public class QuestBoardController {
    
    @Autowired
    QuestBoardService questBoardService;

    // localhost:8080/q/r/board/callDao/list
    // localhost:8080/q/r/board/callDao/list/1
    @GetMapping({"/q/r/board/callDao/list/{pageNumber}","/q/r/board/callDao/list"})
    public ResponseEntity<Object> callboardlist(@PathVariable(required = false) String pageNumber,
                            HashMap<String,Object> dataMap) {
        Object list = questBoardService.selectMany(dataMap);
        return ResponseEntity.ok().body(list);
    }

    // localhost:8080/q/r/board/callDao/insert
    // {
    //     "TITLE" : "Python"
    //     ,"CONTENTS" : "JavaScript"
    //     , "WRITER_ID": "Java"    
    // }
    @GetMapping("/q/r/board/callDao/insert")
    public ResponseEntity<Object> callDaoinsert(@RequestBody HashMap<String,Object> dataMap){
        Object insert = questBoardService.insert(dataMap);
        return ResponseEntity.ok().body(insert);
    }

    // localhost:8080/q/r/board/callDao/update/8121fcdd-4caa-4c41-9705-18f117d5b3bd
    // {
    //     "TITLE" : "lalala"
    // }
    @GetMapping("/q/r/board/callDao/update/{PK_BOARDS}")
    public ResponseEntity<Object> callDaoupdate(@PathVariable String PK_BOARDS,@RequestBody HashMap<String,Object> dataMap){
        Object update = questBoardService.update(dataMap,PK_BOARDS);
        return ResponseEntity.ok().body(update);
    }

    // localhost:8080/q/r/board/callDao/delete/83df4c5d-0beb-45b9-a367-f475d277496e
    @GetMapping("/q/r/board/callDao/delete/{PK_BOARDS}")
    public ResponseEntity<Object> callDaodelete(@PathVariable String PK_BOARDS,@RequestBody HashMap<String,Object> dataMap){
        Object delete = questBoardService.delete(dataMap,PK_BOARDS);
        return ResponseEntity.ok().body(delete);
    }

}
