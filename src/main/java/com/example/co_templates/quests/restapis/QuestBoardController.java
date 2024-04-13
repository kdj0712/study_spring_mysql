package com.example.co_templates.quests.restapis;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.co_templates.quests.services.QuestBoardService;
import com.example.co_templates.quests.services.QuestVisitorsService;


@RestController
public class QuestBoardController {
    
    @Autowired
    QuestBoardService questBoardService;

    @Autowired
    QuestVisitorsService questVisitorsService;  

    @GetMapping("/q/r/board/callDao")
    public void callDao(HashMap dataMap){
        this.questBoardService.selectMany(dataMap);
        this.questBoardService.selectOne(dataMap);
        this.questBoardService.insert(dataMap);
        this.questBoardService.update(dataMap);
        this.questVisitorsService.selectMany(dataMap);
        this.questVisitorsService.selectOne(dataMap);
        this.questVisitorsService.insert(dataMap);
        this.questVisitorsService.update(dataMap);
        this.questVisitorsService.delete(dataMap);
        this.questBoardService.delete(dataMap);

        return ;
    }

}
