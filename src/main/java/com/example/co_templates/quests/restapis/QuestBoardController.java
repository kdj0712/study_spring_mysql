package com.example.co_templates.quests.restapis;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.co_templates.quests.services.QuestBoardService;
// import com.example.co_templates.quests.services.QuestVisitorsService;


@RestController
public class QuestBoardController {
    
    @Autowired
    QuestBoardService questBoardService;

    // @Autowired
    // QuestVisitorsService questVisitorsService;  


    @GetMapping("/q/r/board/callDao")
    public void callDao(HashMap dataMap){
        questBoardService.callDao(dataMap);
        // questVisitorsService.callDao(dataMap);
        return ;
    }
}
