package com.example.co_templates.restapis;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.co_templates.services.BoardService;

@RestController
public class BoardRestController {
    
    @Autowired 
    BoardService BoardService;



    @GetMapping({"/r/Board/List/{pageNumber}","/r/Board/List"})
    public ResponseEntity<Object> callBoardList(@PathVariable(required = false) String pageNumber,
                                @RequestBody HashMap<String,Object> dataMap) {
        Object list = BoardService.list(dataMap);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/r/Board/Insert")
    public void callBoardInsert(HashMap dataMap) {
        BoardService.insert(dataMap);
        return;
    }

    @GetMapping("/r/Board/Update")
    public void callBoardUpdate(HashMap dataMap) {
        BoardService.update(dataMap);
        return;
    }

    @GetMapping("/r/Board/Delete")
    public void callBoardDelete(HashMap dataMap) {
        BoardService.delete(dataMap);
        return;
    }
    
}
